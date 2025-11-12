package dev.sdras.caixasugestoes.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiError> entityNotFoundException(RecursoNaoLocalizadoException ex,
            HttpServletRequest request) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ApiError(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class, RegraDeNegocioException.class })
    public ResponseEntity<ApiError> methodArgumentNotValidException(Exception ex,
            HttpServletRequest request,
            BindingResult result) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ApiError(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalido(s)", result));
    }
}
