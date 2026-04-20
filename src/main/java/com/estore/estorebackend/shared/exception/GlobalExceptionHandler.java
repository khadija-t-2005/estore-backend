package com.estore.estorebackend.shared.exception;

import com.estore.estorebackend.shared.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(
            ResourceNotFoundException ex
    ) {

        ErrorDTO error = new ErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDTO> handleValidation(
            ValidationException ex
    ) {

        ErrorDTO error = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneral(
            Exception ex
    ) {

        ErrorDTO error = new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(
                error,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}