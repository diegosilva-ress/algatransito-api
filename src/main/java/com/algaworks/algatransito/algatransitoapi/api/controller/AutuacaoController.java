package com.algaworks.algatransito.algatransitoapi.api.controller;

import com.algaworks.algatransito.algatransitoapi.api.assembler.AutuacaoAssembler;
import com.algaworks.algatransito.algatransitoapi.api.model.AutuacaoModel;
import com.algaworks.algatransito.algatransitoapi.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.algatransitoapi.domain.model.Autuacao;
import com.algaworks.algatransito.algatransitoapi.domain.model.Veiculo;
import com.algaworks.algatransito.algatransitoapi.domain.service.RegistroAutuacaoService;
import com.algaworks.algatransito.algatransitoapi.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

  private final AutuacaoAssembler autuacaoAssembler;
  private final RegistroAutuacaoService registroAutuacaoService;
  private final RegistroVeiculoService registroVeiculoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AutuacaoModel registrar(@PathVariable Long veiculoId,
      @Valid @RequestBody AutuacaoInput autuacaoInput) {
    Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
    Autuacao autuacaoRegistrada = registroAutuacaoService
        .registrar(veiculoId, novaAutuacao);
    return autuacaoAssembler.toModel(autuacaoRegistrada);
  }

  @GetMapping
  public List<AutuacaoModel> listar(@PathVariable Long veiculoId) {
    Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
    return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
  }

}