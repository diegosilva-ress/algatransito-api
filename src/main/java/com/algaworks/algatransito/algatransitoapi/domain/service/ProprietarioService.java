package com.algaworks.algatransito.algatransitoapi.domain.service;

import com.algaworks.algatransito.algatransitoapi.domain.exception.NegocionException;
import com.algaworks.algatransito.algatransitoapi.domain.model.Proprietario;
import com.algaworks.algatransito.algatransitoapi.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProprietarioService {

  private final ProprietarioRepository proprietarioRepository;

  @Transactional
  public Proprietario salvar(Proprietario proprietario) {
    boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
        .filter(proprietarioExistente -> !proprietarioExistente.equals(proprietario))
        .isPresent();

    if (emailEmUso) {
      throw new NegocionException("Já existe um proprietário cadastrado com o email informado");
    }

    return proprietarioRepository.save(proprietario);
  }

  @Transactional
  public void excluir(Long id) {
    proprietarioRepository.deleteById(id);
  }

}
