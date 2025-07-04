package com.booking.userservice.exception;

import com.booking.userservice.dto.response.ResponseFailure;
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

  @ExceptionHandler({ UserNotFoundException.class, RoleTypeNotFoundException.class })
  public ResponseEntity<ResponseFailure> handleNotFoundException(Exception exception) {
    ResponseFailure response = new ResponseFailure(HttpStatus.NOT_FOUND, exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ UserAlreadyExistsException.class })
  public ResponseEntity<ResponseFailure> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
    ResponseFailure response = new ResponseFailure(HttpStatus.CONFLICT, exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(PasswordsNotMatchException.class)
  public ResponseEntity<ResponseFailure> handlePasswordsNotMatchException(PasswordsNotMatchException exception) {
    ResponseFailure response = new ResponseFailure(HttpStatus.BAD_REQUEST, exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ResponseFailure> handleInvalidCredentialsException(InvalidCredentialsException exception) {
    ResponseFailure response = new ResponseFailure(HttpStatus.UNAUTHORIZED, exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ResponseFailure> handleResponseStatusException(ResponseStatusException exception) {
    ResponseFailure response = new ResponseFailure(HttpStatus.BAD_REQUEST, exception.getReason());
    return new ResponseEntity<>(response, exception.getStatusCode());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseFailure> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    String errorMessage = "Validation failed: " + errors.toString();
    ResponseFailure response = new ResponseFailure(HttpStatus.BAD_REQUEST, errorMessage);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseFailure> handleGenericException(Exception exception) {
    ResponseFailure response = new ResponseFailure(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}