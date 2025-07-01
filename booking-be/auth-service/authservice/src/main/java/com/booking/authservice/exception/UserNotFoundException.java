package com.booking.authservice.exception;

public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException(String message) {
    super(message);
  }
}
