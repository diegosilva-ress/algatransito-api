package com.algaworks.algatransito.algatransitoapi.common;

import com.algaworks.algatransito.algatransitoapi.api.model.VeiculoModel;
import com.algaworks.algatransito.algatransitoapi.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    modelMapper.createTypeMap(Veiculo.class, VeiculoModel.class)
        .addMappings(mapper ->
            mapper.map(Veiculo::getPlaca, VeiculoModel::setNumeroPlaca));

    return modelMapper;
  }

}