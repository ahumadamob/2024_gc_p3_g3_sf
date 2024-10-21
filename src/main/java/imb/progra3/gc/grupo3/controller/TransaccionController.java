package imb.progra3.gc.grupo3.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import imb.progra3.gc.grupo3.util.ApiResponse;
import imb.progra3.gc.grupo3.util.ResponseUtil;
import imb.progra3.gc.grupo3.entity.Transaccion;
import imb.progra3.gc.grupo3.service.ITransaccionService;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping(path = "/api/transacciones")
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Transaccion>>> getAllTransacciones() {
        List<Transaccion> transacciones = transaccionService.findAll();
        return transacciones.isEmpty() ? ResponseUtil.notFound("No se encontraron transacciones")
                : ResponseUtil.success(transacciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaccion>> getTransaccionById(@PathVariable Long id) {
        return transaccionService.exists(id) ? ResponseUtil.success(transaccionService.findById(id))
                : ResponseUtil.notFound("No se encontró la transacción con el ID " + id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Transaccion>> createTransaccion(@RequestBody Transaccion transaccion) {
        if (transaccionService.exists(transaccion.getId())) {
            return ResponseUtil.badRequest("Ya existe una transacción con el ID " + transaccion.getId());
        }
        Transaccion nuevaTransaccion = transaccionService.save(transaccion);
        return ResponseUtil.success(nuevaTransaccion);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaccion>> updateTransaccion(
    		 @PathVariable Long id,@RequestBody Transaccion transaccionDetails) {
        if (transaccionService.exists(id)) {
            Transaccion transaccion = transaccionService.findById(id);
            BeanUtils.copyProperties(transaccionDetails, transaccion);
            return ResponseUtil.success(transaccionService.save(transaccion));
        } else {
            return ResponseUtil.notFound("No existe una transacción con el ID " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteTransaccion(@PathVariable Long id) {
        if (transaccionService.exists(id)) {
            transaccionService.delete(id);
            return ResponseUtil.successDeleted("Se eliminó la transacción con el ID " + id);
        } else {
            return ResponseUtil.badRequest("No se encontró la transacción con el ID " + id);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<Transaccion>>> filterTransacciones(@RequestParam String tipo) {
        List<Transaccion> transacciones = transaccionService.findByTipo(tipo);
        return transacciones.isEmpty() ? ResponseUtil.notFound("No se encontraron transacciones del tipo " + tipo)
                : ResponseUtil.success(transacciones);
    }

    @GetMapping("/greet")
    public ResponseEntity<ApiResponse<String>> greet() {
        return ResponseUtil.success("¡Hola!, Bienvenido a Transacciones.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(new ApiResponse<>(false, ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
}