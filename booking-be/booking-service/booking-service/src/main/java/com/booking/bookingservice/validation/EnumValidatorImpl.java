package com.booking.bookingservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Object> {

  private EnumValidator annotation;

  @Override
  public void initialize(EnumValidator annotation) {
    this.annotation = annotation;
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    Class<? extends Enum<?>> enumClass = annotation.enumClass();
    Object[] enumValues = enumClass.getEnumConstants();

    if (value instanceof String stringValue) {
      for (Object enumValue : enumValues) {
        if (annotation.ignoreCase()) {
          if (stringValue.equalsIgnoreCase(enumValue.toString())) {
            return true;
          }
        } else {
          if (stringValue.equals(enumValue.toString())) {
            return true;
          }
        }
      }
    } else if (enumClass.isAssignableFrom(value.getClass())) {
      return true;
    }

    return false;
  }
}
