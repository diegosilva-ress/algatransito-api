package com.algaworks.algatransito.algatransitoapi.domain.model;

import com.algaworks.algatransito.algatransitoapi.domain.validation.ValidationGroups;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "proprietario")
public class Proprietario {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull(groups = ValidationGroups.ProprietarioId.class)
  private Long id;

  @Column(name = "nome")
  @NotBlank
  @Size(max = 60)
  private String nome;

  @Column(name = "email")
  @Size(max = 255)
  @Email
  private String email;

  @Column(name = "telefone")
  @NotBlank
  @Size(max = 20)
  private String telefone;

}
