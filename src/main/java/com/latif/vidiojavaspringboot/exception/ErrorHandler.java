package com.latif.vidiojavaspringboot.exception;

import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("status", "F");
        result.put("error", "field");
        result.put("message", errors);
        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResMessageDto<String>> handleDataNotFound(RuntimeException exception) {
//        exception.printStackTrace();
        return ResponseEntity.badRequest().body(new ResMessageDto<>(500, (String) exception.getMessage(), null));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResMessageDto<String>> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResMessageDto<>(403, ex.getMessage(), null));
    }

}