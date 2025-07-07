package com.booking.bookingservice.exception;

import com.booking.bookingservice.dto.response.ResponseFailure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ResponseFailure> handleResourceNotFound(ResourceNotFoundException ex) {
    ResponseFailure response = new ResponseFailure(HttpStatus.NOT_FOUND, ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ResponseFailure> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
    ResponseFailure response = new ResponseFailure(HttpStatus.CONFLICT, ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseFailure> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    String errorMessage = "Validation failed: " + errors;
    ResponseFailure response = new ResponseFailure(HttpStatus.BAD_REQUEST, errorMessage);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ResponseFailure> handleResponseStatusException(ResponseStatusException ex) {
    ResponseFailure response = new ResponseFailure(HttpStatus.BAD_REQUEST, ex.getReason());
    return new ResponseEntity<>(response, ex.getStatusCode());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseFailure> handleGenericException(Exception ex) {
    ResponseFailure response = new ResponseFailure(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
