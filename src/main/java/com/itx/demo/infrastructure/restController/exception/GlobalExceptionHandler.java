package com.itx.demo.infrastructure.restController.exception;

import com.itx.demo.domain.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Global exception handler for the application.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentTypeMismatchException exceptions.
     *
     * @param ex the exception
     * @return a ResponseEntity with a 400 status code
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {

        log.error(ErrorCode.INVALID_DATE_FORMAT.getMessage(), ex);
        return ResponseEntity.badRequest().body(ErrorCode.INVALID_DATE_FORMAT.getMessage());
    }

    /**
     * Handles IllegalArgumentException exceptions.
     *
     * @param ex the exception
     * @return a ResponseEntity with a 422 status code
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleMethodIllegalArgumentException(IllegalArgumentException ex) {

        log.error(ErrorCode.UNPROCESABLE_ENTITY.getMessage(), ex);
        return ResponseEntity.unprocessableEntity().body(ErrorCode.UNPROCESABLE_ENTITY.getMessage());
    }

    /**
     * Handles AuthorizationDeniedException exceptions.
     *
     * @param ex the exception
     * @return a ResponseEntity with a 403 status code
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<String> handleMethodException(AuthorizationDeniedException ex) {

        log.error(ErrorCode.UNAUTHORIZED_ERROR.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorCode.UNAUTHORIZED_ERROR.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleMethodException(Exception ex) {

        log.error(ErrorCode.UNEXPECTED_ERROR.getMessage(), ex);
        return ResponseEntity.internalServerError().body(ErrorCode.UNEXPECTED_ERROR.getMessage());
    }

}