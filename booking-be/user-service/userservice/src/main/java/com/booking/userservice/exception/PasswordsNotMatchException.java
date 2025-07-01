package com.booking.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordsNotMatchException extends RuntimeException {
  public PasswordsNotMatchException() {
    super("Passwords do not match");
  }
}
