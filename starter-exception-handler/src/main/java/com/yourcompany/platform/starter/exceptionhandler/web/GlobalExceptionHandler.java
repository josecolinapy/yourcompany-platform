package com.yourcompany.platform.starter.exceptionhandler.web;

import com.yourcompany.platform.common.errors.exception.PlatformException;
import com.yourcompany.platform.common.errors.model.ErrorCode;
import com.yourcompany.platform.common.logging.constants.LoggingConstants;
import com.yourcompany.platform.common.web.ApiError;
import com.yourcompany.platform.common.web.ErrorResponse;
import com.yourcompany.platform.common.web.ResponseMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;


/*
 * @author josec
 * @project yourcompany-platform
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PlatformException.class)
    public ResponseEntity<ErrorResponse> handlePlatformException(
            final PlatformException exception
    ) {
        final HttpStatus status = mapStatus(exception.getErrorCode());

        return ResponseEntity.status(status).body(
                ErrorResponse.failure(
                        new ApiError(exception.getErrorCode(), exception.getMessage(), Map.of()),
                        buildMetadata()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(
                ErrorResponse.failure(
                        new ApiError(ErrorCode.VALIDATION_ERROR, "Request validation failed", Map.of()),
                        buildMetadata()
                )
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(final ConstraintViolationException exception) {
        return ResponseEntity.badRequest().body(
                ErrorResponse.failure(
                        new ApiError(ErrorCode.VALIDATION_ERROR, "Constraint validation failed", Map.of()),
                        buildMetadata()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(final Exception exception, final HttpServletRequest request) {
        LOGGER.error("Unhandled exception", exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.failure(
                        new ApiError(ErrorCode.INTERNAL_ERROR, "Unexpected internal error", Map.of()),
                        buildMetadata()
                )
        );
    }

    private ResponseMetadata buildMetadata() {
        return new ResponseMetadata(Instant.now(), MDC.get(LoggingConstants.CORRELATION_ID), MDC.get(LoggingConstants.TRACE_ID));
    }

    private HttpStatus mapStatus(final ErrorCode errorCode) {
        return switch (errorCode) {
            case VALIDATION_ERROR, BAD_REQUEST -> HttpStatus.BAD_REQUEST;
            case RESOURCE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case CONFLICT -> HttpStatus.CONFLICT;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case BUSINESS_RULE_VIOLATION -> HttpStatus.UNPROCESSABLE_ENTITY;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
