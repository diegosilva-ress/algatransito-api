package com.algaworks.algatransito.algatransitoapi.api.controller;

import com.algaworks.algatransito.algatransitoapi.domain.exception.NegocioException;
import com.algaworks.algatransito.algatransitoapi.domain.model.Proprietario;
import com.algaworks.algatransito.algatransitoapi.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.algatransitoapi.domain.service.ProprietarioService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proprietarios")
@AllArgsConstructor
public class ProprietarioController {

  private final ProprietarioService proprietarioService;
  private final ProprietarioRepository proprietarioRepository;

  @GetMapping
  public List<Proprietario> listar() {
    return proprietarioRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Proprietario> buscarPorId(@PathVariable Long id) {
    return proprietarioRepository.findById(id).map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario) {
    return proprietarioService.salvar(proprietario);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Proprietario> atualizar(@PathVariable Long id,
      @Valid @RequestBody Proprietario proprietario) {

    if (proprietarioRepository.existsById(id)) {
      proprietario.setId(id);
      return ResponseEntity.ok(proprietarioService.salvar(proprietario));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Long id) {
    if (proprietarioRepository.existsById(id)) {
      proprietarioService.excluir(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

}
