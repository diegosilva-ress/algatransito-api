package com.algaworks.algatransito.algatransitoapi.api.exception;

import com.algaworks.algatransito.algatransitoapi.domain.exception.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerApi {

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<String> capturar(NegocioException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

}
