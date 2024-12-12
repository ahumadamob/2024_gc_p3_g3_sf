package imb.progra3.gc.grupo3.controller;

import imb.progra3.gc.grupo3.dto.CuentaDTO;
import imb.progra3.gc.grupo3.entity.Cuenta;
import imb.progra3.gc.grupo3.service.ICuentaService;
import imb.progra3.gc.grupo3.util.APIResponse;
import imb.progra3.gc.grupo3.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/cuenta")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

    // GET para obtener todas las cuentas
    @GetMapping
    public ResponseEntity<APIResponse<List<Cuenta>>> getAll() {
        List<Cuenta> cuentas = cuentaService.getAll();
        return ResponseUtil.success(cuentas);
    }

    // GET para obtener una cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Cuenta>> getById(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.findById(id);
        return cuenta != null ? ResponseUtil.success(cuenta) : ResponseUtil.notFound("Cuenta no encontrada");
    }

    // POST para crear una cuenta
    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            // Llamada al servicio para crear la cuenta
            Cuenta nuevaCuenta = cuentaService.crearCuenta(cuentaDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "Cuenta creada exitosamente",
                    "data", nuevaCuenta
            ));
        } catch (IllegalArgumentException e) {
            // En caso de que falle la validación, se devuelve un mensaje adecuado con código 400
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    // PUT para actualizar una cuenta
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaService.getById(id);
        if (cuenta != null) {
            cuenta.setFechaApertura(cuentaDetails.getFechaApertura());
            cuenta.setId(cuentaDetails.getId());
            cuenta.setIdCliente(cuentaDetails.getIdCliente());
            cuenta.setSaldo(cuentaDetails.getSaldo());
            cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
            Cuenta updatedCuenta = cuentaService.save(cuenta);
            return ResponseEntity.ok(updatedCuenta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT para actualizar el saldo de una cuenta
    @PutMapping("/{id}/actualizarSaldo")
    public ResponseEntity<String> actualizarSaldo(@PathVariable Long id, @RequestBody Double nuevoSaldo) {
        boolean actualizado = cuentaService.actualizarSaldo(id, nuevoSaldo);
        if (actualizado) {
            return ResponseEntity.ok("Saldo actualizado correctamente.");
        } else {
            return ResponseEntity.badRequest().body("No se pudo actualizar el saldo. La cuenta no existe o el saldo es inválido.");
        }
    }

    // DELETE para eliminar una cuenta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        if (cuentaService.exists(id)) {
            cuentaService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Excepción global para manejar errores genéricos
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleException(Exception ex) {
        return ResponseUtil.badRequest(ex.getMessage());
    }

    // Excepción para manejar violaciones de validación
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Cuenta>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
}
