package com.nutritrack.controller;

import com.nutritrack.dto.rda.RdaResponse;
import com.nutritrack.dto.rda.RdaUpdateRequest;
import com.nutritrack.service.RdaService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rdas")
public class RdaController {
  private final RdaService rdaService;

  public RdaController(RdaService rdaService) {
    this.rdaService = rdaService;
  }

  @GetMapping
  public List<RdaResponse> list() {
    return rdaService.list();
  }

  @PutMapping("/{id}")
  public RdaResponse update(@PathVariable String id, @RequestBody RdaUpdateRequest req) {
    return rdaService.update(id, req);
  }
}

