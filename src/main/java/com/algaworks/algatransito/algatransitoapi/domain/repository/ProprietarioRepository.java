package com.algaworks.algatransito.algatransitoapi.domain.repository;

import com.algaworks.algatransito.algatransitoapi.domain.model.Proprietario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

  List<Proprietario> findByNome(String nome);

}
