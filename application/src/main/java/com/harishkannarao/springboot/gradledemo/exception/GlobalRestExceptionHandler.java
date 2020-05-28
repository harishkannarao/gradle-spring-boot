package com.harishkannarao.springboot.gradledemo.exception;

import com.harishkannarao.springboot.gradledemo.dto.ValidationErrorDto;
import com.harishkannarao.springboot.gradledemo.dto.ValidationErrorResponseDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestControllerAdvice(annotations = {RestController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalRestExceptionHandler {

    @SuppressWarnings("unused")
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    public ResponseEntity<ValidationErrorResponseDto> handleConException(HttpServletRequest request, ConstraintViolationException e) {
        List<ValidationErrorDto> errors = e.getConstraintViolations().stream()
                .map(violation -> ValidationErrorDto.builder()
                        .message(violation.getMessage())
                        .propertyPath(violation.getPropertyPath().toString())
                        .build())
                .collect(Collectors.toList());
        ValidationErrorResponseDto errorResponse = ValidationErrorResponseDto.builder()
                .validationErrors(errors)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
