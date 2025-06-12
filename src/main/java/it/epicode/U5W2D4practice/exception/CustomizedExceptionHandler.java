package it.epicode.U5W2D4practice.exception;

import org.springframework.http.HttpStatus;
import it.epicode.U5W2D4practice.model.ApiError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomizedExceptionHandler {

  @ExceptionHandler(AutoreNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiError handleAutoreNotFound(AutoreNotFoundException e) {
    ApiError error = new ApiError();
    error.setMessage("Autore non trovato: " + e.getMessage());
    error.setDataErrore(LocalDateTime.now());
    return error;
  }

  @ExceptionHandler(BlogPostNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiError handleBlogPostNotFound(BlogPostNotFoundException e) {
    ApiError error = new ApiError();
    error.setMessage("Blog post non trovato: " + e.getMessage());
    error.setDataErrore(LocalDateTime.now());
    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handleValidation(MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(" | "));

    ApiError error = new ApiError();
    error.setMessage("Errore di validazione: " + message);
    error.setDataErrore(LocalDateTime.now());
    return error;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiError handleGeneric(Exception e) {
    ApiError error = new ApiError();
    error.setMessage("Errore interno: " + e.getMessage());
    error.setDataErrore(LocalDateTime.now());
    return error;
  }
}
