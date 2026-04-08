package com.nutritrack.service;

import com.nutritrack.dto.rda.RdaResponse;
import com.nutritrack.dto.rda.RdaUpdateRequest;
import com.nutritrack.exception.ApiException;
import com.nutritrack.model.Rda;
import com.nutritrack.repo.RdaRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RdaService {
  private final RdaRepository rdaRepository;
  private final ModelMapper modelMapper;

  public RdaService(RdaRepository rdaRepository, ModelMapper modelMapper) {
    this.rdaRepository = rdaRepository;
    this.modelMapper = modelMapper;
  }

  public List<RdaResponse> list() {
    return rdaRepository.findAll().stream().map(r -> modelMapper.map(r, RdaResponse.class)).toList();
  }

  public RdaResponse update(String id, RdaUpdateRequest req) {
    Rda rda = rdaRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "not found"));
    if (req.getCalories() != null) rda.setCalories(req.getCalories());
    if (req.getProteinG() != null) rda.setProteinG(req.getProteinG());
    if (req.getIronMg() != null) rda.setIronMg(req.getIronMg());
    if (req.getVitaminCMg() != null) rda.setVitaminCMg(req.getVitaminCMg());
    if (req.getCalciumMg() != null) rda.setCalciumMg(req.getCalciumMg());
    if (req.getVitaminDIu() != null) rda.setVitaminDIu(req.getVitaminDIu());
    rdaRepository.save(rda);
    return modelMapper.map(rda, RdaResponse.class);
  }
}

