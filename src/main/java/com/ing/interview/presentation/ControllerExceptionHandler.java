package com.ing.interview.presentation;

import com.ing.interview.service.NotDefaultColorException;
import com.ing.interview.service.NotEligibleInsuranceException;
import com.ing.interview.service.StockNotAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(StockNotAvailableException.class)
    public ResponseEntity<Error> stockNotAvailableExceptionHandler(StockNotAvailableException ex) {
        log.info(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Error.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(HttpStatus.NOT_FOUND.name())
                        .description(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(NotEligibleInsuranceException.class)
    public ResponseEntity<Error> notEligibleInsuranceExceptionHandler(NotEligibleInsuranceException ex) {
        log.info(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.name())
                        .description(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> illegalArgumentModelHandler(IllegalArgumentException ex) {
        log.info(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.name())
                        .description("Model not supported")
                        .build());
    }

    @ExceptionHandler(NotDefaultColorException.class)
    public ResponseEntity<Error> notDefaultColorExceptionHandler(NotDefaultColorException ex) {
        log.info(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Error.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(HttpStatus.NOT_FOUND.name())
                        .description(ex.getMessage())
                        .build());
    }

}
