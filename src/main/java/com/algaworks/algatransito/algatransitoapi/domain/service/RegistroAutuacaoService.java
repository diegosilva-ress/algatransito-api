package com.algaworks.algatransito.algatransitoapi.domain.service;

import com.algaworks.algatransito.algatransitoapi.domain.model.Autuacao;
import com.algaworks.algatransito.algatransitoapi.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {

  private RegistroVeiculoService registroVeiculoService;

  @Transactional
  public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao) {
    Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
    return veiculo.adicionarAutuacao(novaAutuacao);
  }

}