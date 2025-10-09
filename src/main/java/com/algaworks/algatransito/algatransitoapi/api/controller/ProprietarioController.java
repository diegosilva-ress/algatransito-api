package com.algaworks.algatransito.algatransitoapi.api.controller;

import com.algaworks.algatransito.algatransitoapi.domain.model.Proprietario;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProprietarioController {

  @GetMapping("/proprietarios")
  public List<Proprietario> listar() {
    var proprietarios = List.of(new Proprietario());
    return proprietarios;
  }

}
