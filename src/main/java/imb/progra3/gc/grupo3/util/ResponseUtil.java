import org.springframework.http.ResponseEntity;
import imb.progra3.gc.grupo3.util.APIResponse;
import jakarta.validation.ConstraintViolationException;

public class ResponseUtil {

    public static <T> ResponseEntity<APIResponse<T>> success(T data) {
        return ResponseEntity.ok(new APIResponse<>(true, "Exitoso", data));
    }

    public static <T> ResponseEntity<APIResponse<T>> notFound(String message) {
        return ResponseEntity.status(404).body(new APIResponse<>(false, message, null));
    }

    public static <T> ResponseEntity<APIResponse<T>> badRequest(String message) {
        return ResponseEntity.status(400).body(new APIResponse<>(false, message, null));
    }

    public static ResponseEntity<APIResponse<Object>> handleConstraintException(ConstraintViolationException ex) {
        return badRequest("Error de Validacion: " + ex.getMessage());
    }

    public static ResponseEntity<APIResponse<Object>> successDeleted(String message, Long id) {
        return ResponseEntity.ok(new APIResponse<>(true, message.replace("{0}", id.toString()), null));
    }
}