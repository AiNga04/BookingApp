package com.booking.hotelservice.exception;

import com.booking.hotelservice.dto.ResponseFailure;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler({ResourceNotFoundException.class})
  public ResponseFailure handleResourceNotFoundException(ResourceNotFoundException exception) {
    return new ResponseFailure(HttpStatus.NOT_FOUND, exception.getMessage());
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseFailure handleRuntimeException(RuntimeException exception) {
    return new ResponseFailure(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    return ResponseEntity.badRequest().body(errors);
  }
}
