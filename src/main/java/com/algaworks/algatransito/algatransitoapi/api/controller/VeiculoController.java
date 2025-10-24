package com.algaworks.algatransito.algatransitoapi.api.controller;

import com.algaworks.algatransito.algatransitoapi.api.model.VeiculoModel;
import com.algaworks.algatransito.algatransitoapi.domain.model.Veiculo;
import com.algaworks.algatransito.algatransitoapi.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.algatransitoapi.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  private final VeiculoRepository veiculoRepository;
  private final RegistroVeiculoService registroVeiculoService;
  private final ModelMapper modelMapper;

  @GetMapping
  public List<Veiculo> listar() {
    return veiculoRepository.findAll();
  }

  @GetMapping("/{veiculoId}")
  public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId) {
    return veiculoRepository.findById(veiculoId)
        .map(veiculo -> modelMapper.map(veiculo, VeiculoModel.class))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
    return registroVeiculoService.cadastrar(veiculo);
  }

}