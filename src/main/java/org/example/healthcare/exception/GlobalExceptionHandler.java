package org.example.healthcare.exception;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
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
   @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(){
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("incorrect eamil or password");

   }
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException() {
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT signature");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException() {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token has expired");
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex){
       return ResponseEntity.status(HttpStatus.FORBIDDEN)
               .body("Access Denied : You don't have permission");
    }


}
