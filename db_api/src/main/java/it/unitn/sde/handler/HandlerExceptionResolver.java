package it.unitn.sde.handler;

import org.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerExceptionResolver {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFoundResourceException() {
        JSONObject header = new JSONObject()
                .put("status", "error")
                .put("status_code", HttpStatus.NOT_FOUND.value())
                .put("description", "wrong endpoint");
        JSONObject response = new JSONObject()
                .put("header", header);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response.toMap());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException() {
        JSONObject header = new JSONObject()
                .put("status", "error")
                .put("status_code", HttpStatus.BAD_REQUEST.value())
                .put("description", "invalid json");
        JSONObject response = new JSONObject()
                .put("header", header);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response.toMap());
    }
}
