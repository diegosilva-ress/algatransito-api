package com.algaworks.algatransito.algatransitoapi.api.controller;

import com.algaworks.algatransito.algatransitoapi.domain.model.Proprietario;
import com.algaworks.algatransito.algatransitoapi.domain.repository.ProprietarioRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proprietarios")
@AllArgsConstructor
public class ProprietarioController {

  private final ProprietarioRepository proprietarioRepository;

  public List<Proprietario> listar() {
    return proprietarioRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Proprietario> buscarPorId(@PathVariable Long id) {
    return proprietarioRepository.findById(id).map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
