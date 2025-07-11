package com.booking.authservice.exception;

import com.booking.authservice.dto.response.ErrorResponse;
import com.booking.authservice.dto.response.ResponseFailure;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({UserNotFoundException.class})
  public ResponseFailure handleStudentNotFoundException(UserNotFoundException exception) {
    return new ResponseFailure(HttpStatus.NOT_FOUND, exception.getMessage());
  }

  @ExceptionHandler({UserAlreadyExistsException.class})
  public ResponseFailure handleStudentAlreadyExistsException(UserAlreadyExistsException exception) {
    return new ResponseFailure(HttpStatus.CONFLICT, exception.getMessage());
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseFailure handleRuntimeException(RuntimeException exception) {
    return new ResponseFailure(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationException(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
    ErrorResponse error = new ErrorResponse(ex.getStatusCode().value(), ex.getReason());
    return new ResponseEntity<>(error, ex.getStatusCode());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "An unexpected error occurred");
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
