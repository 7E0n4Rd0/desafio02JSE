package com.devsuperior.demo.controllers.exceptions;

import com.devsuperior.demo.services.exceptions.DatabaseException;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        int notFound = HttpStatus.NOT_FOUND.value();
        StandardError err = new StandardError(
                Instant.now(), notFound, "Resource not found", e.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(notFound).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request){
        int badRequest = HttpStatus.BAD_REQUEST.value();
        StandardError err = new StandardError(
            Instant.now(), badRequest, "Integrity Violation", e.getMessage(),request.getRequestURI()
        );
        return ResponseEntity.status(badRequest).body(err);
    }
}
