package com.interview.fraudalert.exception;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex){
    Map<String,String> errors=new LinkedHashMap<>(); ex.getBindingResult().getFieldErrors().forEach(e->errors.put(e.getField(), e.getDefaultMessage()));
    return ResponseEntity.badRequest().body(new ApiError(Instant.now(),400,"Bad Request","Validation failed",errors));
  }
  @ExceptionHandler(KafkaPublishException.class)
  ResponseEntity<ApiError> handleKafka(KafkaPublishException ex){ return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ApiError(Instant.now(),503,"Service Unavailable",ex.getMessage(),Map.of())); }
  @ExceptionHandler(Exception.class)
  ResponseEntity<ApiError> handleOther(Exception ex){ return ResponseEntity.status(500).body(new ApiError(Instant.now(),500,"Internal Server Error","Unexpected error",Map.of())); }
}
