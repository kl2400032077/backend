package com.nutritrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "health_records")
public class HealthRecord {
  @Id
  private String id;

  @Column(nullable = false)
  private String userId;

  private int age;

  @Column(columnDefinition = "LONGTEXT")
  private String totalsJson;

  @Column(columnDefinition = "LONGTEXT")
  private String deficitsJson;

  private long timestamp;

  public HealthRecord() {}

  public HealthRecord(String id, String userId, int age, String totalsJson, String deficitsJson, long timestamp) {
    this.id = id;
    this.userId = userId;
    this.age = age;
    this.totalsJson = totalsJson;
    this.deficitsJson = deficitsJson;
    this.timestamp = timestamp;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getTotalsJson() {
    return totalsJson;
  }

  public void setTotalsJson(String totalsJson) {
    this.totalsJson = totalsJson;
  }

  public String getDeficitsJson() {
    return deficitsJson;
  }

  public void setDeficitsJson(String deficitsJson) {
    this.deficitsJson = deficitsJson;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}

