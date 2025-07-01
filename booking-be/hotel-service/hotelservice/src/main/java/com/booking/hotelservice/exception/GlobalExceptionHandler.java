package com.booking.hotelservice.exception;

import com.booking.hotelservice.dto.response.ResponseFailure;
import com.booking.hotelservice.dto.response.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

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

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<Object> handleHandlerMethodValidationException(
      HandlerMethodValidationException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.visitResults(new HandlerMethodValidationException.Visitor() {
      @Override
      public void requestBody(org.springframework.web.bind.annotation.RequestBody requestBody,
          org.springframework.validation.method.ParameterErrors parameterErrors) {
        parameterErrors.getFieldErrors().forEach(fieldError ->
            errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
      }

      @Override
      public void pathVariable(org.springframework.web.bind.annotation.PathVariable pathVariable,
          org.springframework.validation.method.ParameterValidationResult parameterResult) {
        String parameterName = parameterResult.getMethodParameter().getParameterName();
        parameterResult.getResolvableErrors().forEach(error ->
            errors.put(parameterName != null ? parameterName : "parameter",
                error.getDefaultMessage())
        );
      }

      @Override
      public void requestParam(org.springframework.web.bind.annotation.RequestParam requestParam,
          org.springframework.validation.method.ParameterValidationResult parameterResult) {
        String parameterName = parameterResult.getMethodParameter().getParameterName();
        parameterResult.getResolvableErrors().forEach(error ->
            errors.put(parameterName != null ? parameterName : "parameter",
                error.getDefaultMessage())
        );
      }

      @Override
      public void cookieValue(org.springframework.web.bind.annotation.CookieValue cookieValue,
          org.springframework.validation.method.ParameterValidationResult result) {
      }

      @Override
      public void matrixVariable(
          org.springframework.web.bind.annotation.MatrixVariable matrixVariable,
          org.springframework.validation.method.ParameterValidationResult result) {
      }

      @Override
      public void modelAttribute(
          org.springframework.web.bind.annotation.ModelAttribute modelAttribute,
          org.springframework.validation.method.ParameterErrors errors) {
      }

      @Override
      public void requestHeader(org.springframework.web.bind.annotation.RequestHeader requestHeader,
          org.springframework.validation.method.ParameterValidationResult result) {
      }

      @Override
      public void requestPart(org.springframework.web.bind.annotation.RequestPart requestPart,
          org.springframework.validation.method.ParameterErrors errors) {
      }

      @Override
      public void other(org.springframework.validation.method.ParameterValidationResult result) {
      }
    });

    ErrorResponse errorResponse = new ErrorResponse(
        "There was a problem during data check",
        errors
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidException(MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    ErrorResponse errorResponse = new ErrorResponse("There was a problem during data check",
        errors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
