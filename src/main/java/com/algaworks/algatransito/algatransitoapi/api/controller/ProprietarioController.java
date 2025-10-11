package com.algaworks.algatransito.algatransitoapi.api.controller;

import com.algaworks.algatransito.algatransitoapi.domain.model.Proprietario;
import com.algaworks.algatransito.algatransitoapi.domain.repository.ProprietarioRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProprietarioController {

  private final ProprietarioRepository proprietarioRepository;

  @GetMapping("/proprietarios")
  public List<Proprietario> listar() {
//    return proprietarioRepository.findAll();
    return proprietarioRepository.findByNome("Diego");
  }

}
