package ru.trush.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
@Generated
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleTokenException(final TokenException e) {
        log.info("400 {}", e.getMessage(), e);
        return new ApiError("BAD_REQUEST", "Token Exception", e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDataIntegrityException(final DataIntegrityViolationException e) {
        log.info("409 {}", e.getMessage(), e);
        return new ApiError("CONFLICT", "Data integrity violation", e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.info("400 {}", e.getMessage(), e);
        return new ApiError("BAD_REQUEST", "Incorrect request", e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMissingRequestParameterException(final MissingServletRequestParameterException e) {
        log.info("400 {}", e.getMessage(), e);
        return new ApiError("BAD_REQUEST", "Missing Parameter", e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowableExceptions(final Exception e) {
        log.info("500 {}", e.getMessage(), e);
        return new ApiError("INTERNAL_SERVER_ERROR", "Ooops, something went wrong", e.getMessage(),
                LocalDateTime.now());
    }


    record ApiError(String status, String reason, String message,
                    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp) {
        ApiError(String status, String reason, String message, LocalDateTime timestamp) {
            this.status = status;
            this.reason = reason;
            this.message = message;
            this.timestamp = timestamp;
        }
    }
}
