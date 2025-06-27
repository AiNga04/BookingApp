package com.booking.userservice.exception;

import com.booking.userservice.dto.response.ResponseFailure;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
