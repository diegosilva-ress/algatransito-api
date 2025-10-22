package com.algaworks.algatransito.algatransitoapi.domain.model;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import com.algaworks.algatransito.algatransitoapi.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @ManyToOne
  @JoinColumn(name = "proprietario_id")
  @NotNull
  @Valid
  @ConvertGroup(to = ValidationGroups.ProprietarioId.class)
  private Proprietario proprietario;

  @NotBlank
  private String marca;

  @NotBlank
  private String modelo;

  @NotBlank
  @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
  private String placa;

  @Enumerated(EnumType.STRING)
  @JsonProperty(access = READ_ONLY)
  private StatusVeiculo status;

  @JsonProperty(access = READ_ONLY)
  private OffsetDateTime dataCadastro;

  @JsonProperty(access = READ_ONLY)
  private OffsetDateTime dataApreensao;

}