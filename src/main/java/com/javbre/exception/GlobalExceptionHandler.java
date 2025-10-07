package com.javbre.exception;

import com.javbre.utilities.Utilities;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.javbre.utilities.Constants.BAD_CONNECTIVITY_MESSAGE;
import static com.javbre.utilities.Shield.blindStr;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ExceptionMessage> handleBadRequestException(BadRequestException ex,
                                                                      HttpServletRequest request) {
        LOGGER.error("Error en el servicio: {}", ex.getMessage());
        var response = new ExceptionMessage();
        response.setTimestamp(Utilities.getTimestampValue());
        response.setMessage(blindStr(ex.getMessage()));
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ExceptionMessage> handleSQLException(NotFoundException ex,
                                                               HttpServletRequest request) {
        LOGGER.error("Error en el servicio: {}", ex.getMessage());
        var response = new ExceptionMessage();
        response.setTimestamp(Utilities.getTimestampValue());
        response.setMessage(blindStr(ex.getMessage()));
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingHeadersException.class)
    @ResponseBody
    public ResponseEntity<ExceptionMessage> handleMissingHeadersException(MissingHeadersException ex,
                                                                          HttpServletRequest request) {
        LOGGER.error("Error en el servicio: {}", ex.getMessage());
        var response = new ExceptionMessage();
        response.setTimestamp(Utilities.getTimestampValue());
        response.setMessage(blindStr(ex.getMessage()));
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ExceptionMessage> methodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        // Extrae todos los errores de validación con detalle
        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format("Campo '%s': %s", error.getField(), error.getDefaultMessage()))
                .toList();

        // Imprime los errores completos en el log
        LOGGER.warn("Error validando campos de entrada: {}", errores);

        // Crea la respuesta al cliente
        var response = new ExceptionMessage();
        response.setTimestamp(Utilities.getTimestampValue());
        response.setMessage(blindStr(errores.getFirst()));
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionMessage> handleException(Exception ex,
                                                            HttpServletRequest request) {
        LOGGER.error("Error general del servicio: {}", ex.getMessage());
        var response = new ExceptionMessage();
        response.setTimestamp(Utilities.getTimestampValue());
        response.setMessage(blindStr(BAD_CONNECTIVITY_MESSAGE));
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
