package com.nutritrack.service;

import com.nutritrack.exception.ApiException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
  private static final long OTP_TTL_SECONDS = 10 * 60;
  private final JavaMailSender mailSender;
  private final SecureRandom random = new SecureRandom();
  private final Map<String, OtpEntry> otpByEmail = new ConcurrentHashMap<>();

  @Value("${app.mail.from:}")
  private String fromEmail;

  public OtpService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void issueSignupOtp(String email) {
    String normalizedEmail = normalize(email);
    String otp = String.format("%06d", random.nextInt(1_000_000));
    otpByEmail.put(normalizedEmail, new OtpEntry(otp, Instant.now().plusSeconds(OTP_TTL_SECONDS)));
    sendEmail(normalizedEmail, otp);
  }

  public void verifySignupOtp(String email, String otp) {
    String normalizedEmail = normalize(email);
    OtpEntry entry = otpByEmail.get(normalizedEmail);
    if (entry == null) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "OTP not requested for this email");
    }
    if (Instant.now().isAfter(entry.expiresAt())) {
      otpByEmail.remove(normalizedEmail);
      throw new ApiException(HttpStatus.BAD_REQUEST, "OTP expired. Please request a new OTP");
    }
    if (!entry.otp().equals(otp)) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid OTP");
    }
    otpByEmail.remove(normalizedEmail);
  }

  private void sendEmail(String toEmail, String otp) {
    try {
      SimpleMailMessage msg = new SimpleMailMessage();
      if (fromEmail != null && !fromEmail.isBlank()) {
        msg.setFrom(fromEmail.trim());
      }
      msg.setTo(toEmail);
      msg.setSubject("NutriTrack Signup OTP");
      msg.setText("Your NutriTrack OTP is: " + otp + "\nIt is valid for 10 minutes.");
      mailSender.send(msg);
    } catch (MailException ex) {
      throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to send OTP email. Check SMTP configuration.");
    }
  }

  private String normalize(String email) {
    return email == null ? "" : email.trim().toLowerCase();
  }

  private record OtpEntry(String otp, Instant expiresAt) {}
}

