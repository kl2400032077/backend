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
    if (req.calories() != null) rda.setCalories(req.calories());
    if (req.proteinG() != null) rda.setProteinG(req.proteinG());
    if (req.ironMg() != null) rda.setIronMg(req.ironMg());
    if (req.vitaminCMg() != null) rda.setVitaminCMg(req.vitaminCMg());
    if (req.calciumMg() != null) rda.setCalciumMg(req.calciumMg());
    if (req.vitaminDIu() != null) rda.setVitaminDIu(req.vitaminDIu());
    rdaRepository.save(rda);
    return modelMapper.map(rda, RdaResponse.class);
  }
}

