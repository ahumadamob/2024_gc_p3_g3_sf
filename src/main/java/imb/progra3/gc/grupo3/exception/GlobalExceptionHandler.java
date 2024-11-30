package imb.progra3.gc.grupo3.exception;

import imb.progra3.gc.grupo3.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<APIResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        APIResponse<String> response = new APIResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                Collections.singletonList(ex.getMessage()),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
