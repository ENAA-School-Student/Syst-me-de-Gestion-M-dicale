package org.example.healthcare.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handelerValidateErrours(MethodArgumentNotValidException ex){
       List<String> errors=new ArrayList<>();
       ex.getBindingResult().getAllErrors().forEach(er->{
           String field=((FieldError) er).getField();
           String message=er.getDefaultMessage();
           errors.add(field +": "+ message);
       });
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
   }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
   }

}
