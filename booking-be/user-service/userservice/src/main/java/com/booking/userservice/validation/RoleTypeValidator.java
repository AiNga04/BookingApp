package com.booking.userservice.validation;

import com.booking.userservice.enums.RoleType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RoleTypeValidator implements ConstraintValidator<ValidRoleType, RoleType> {
  @Override
  public boolean isValid(RoleType value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    return Arrays.asList(RoleType.values()).contains(value);
  }
}