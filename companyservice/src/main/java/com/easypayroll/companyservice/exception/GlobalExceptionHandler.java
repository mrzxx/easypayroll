package com.easypayroll.companyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DeleteResrictedException.class)
    public ResponseEntity<String> handleDeletionRestrictedException(DeleteResrictedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    
}
