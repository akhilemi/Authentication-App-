package com.akhilprog.authapp.exceptions;

import com.akhilprog.authapp.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //resource not found exception
    @ExceptionHandler(ResourceNotFounDException.class)
    public ResponseEntity<ErrorResponse> handleresourceNotFounDException(ResourceNotFounDException exception) {
        ErrorResponse internalServerIssue = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalServerIssue);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegarArgumentException(IllegalArgumentException exception) {
        ErrorResponse ilegalArgumentissue = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ilegalArgumentissue);
    }
}
