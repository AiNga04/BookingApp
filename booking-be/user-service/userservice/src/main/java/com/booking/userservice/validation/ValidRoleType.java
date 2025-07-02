package com.booking.userservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleTypeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRoleType {
  String message() default "Invalid RoleType";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}