package br.com.jota.shophub.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratorDeError {
    @ExceptionHandler(RegraDeNegorcioException.class)
    public ResponseEntity<String> tratarErrorRegraDeNegocio(RegraDeNegorcioException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
