package imb.progra3.gc.grupo3.util;

import org.springframework.http.ResponseEntity;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;


public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Exitoso", data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return ResponseEntity.status(404).body(new ApiResponse<>(false, message, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return ResponseEntity.status(400).body(new ApiResponse<>(false, message, null));
    }

    public static ResponseEntity<ApiResponse<Object>> handleConstraintException(ConstraintViolationException ex) {
        return badRequest("Error de Validacion: " + ex.getMessage());
    }

    public static ResponseEntity<ApiResponse<Object>> successDeleted(String message, Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, message.replace("{0}", id.toString()), null));
    }
    
    public static ResponseEntity<ApiResponse<Object>> successDeleted(String message) {
        return new ResponseEntity<>(new ApiResponse<>(true, message, null), HttpStatus.OK);
    }

}