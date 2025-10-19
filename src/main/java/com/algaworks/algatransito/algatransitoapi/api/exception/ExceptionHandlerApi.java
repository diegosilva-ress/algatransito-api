package com.algaworks.algatransito.algatransitoapi.api.exception;

import com.algaworks.algatransito.algatransitoapi.domain.exception.NegocioException;
import java.net.URI;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@AllArgsConstructor
public class ExceptionHandlerApi extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    ProblemDetail problemDetail = ProblemDetail.forStatus(status);
    problemDetail.setDetail("Um ou mais campos estão inválidos.");
    problemDetail.setType(URI.create("https://www.algatransito.com/errors/campos-invalidos"));

    var fields = ex.getBindingResult().getFieldErrors()
        .stream()
        .collect(Collectors.toMap(FieldError::getField,
            objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

    problemDetail.setProperty("fields", fields);

    return handleExceptionInternal(ex, problemDetail, headers, status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<String> capturar(NegocioException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

}
