package imb.progra3.gc.grupo3.controller;
import imb.progra3.gc.grupo3.entity.Cuenta;
import imb.progra3.gc.grupo3.service.ICuentaService;
import imb.progra3.gc.grupo3.util.APIResponse;
import imb.progra3.gc.grupo3.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

@RestController
@RequestMapping(path="api/cuenta")
public class CuentaController {
    @Autowired
    private ICuentaService cuentaService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Cuenta>>> getAll() {
        List<Cuenta> cuentas = cuentaService.getAll();
        return ResponseUtil.success(cuentas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Cuenta>> getById(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.findById(id);
        return cuenta != null ? ResponseUtil.success(cuenta) : ResponseUtil.notFound("Cuenta no encontrada");
    }




    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.save(cuenta);
    }
    
    @PutMapping
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        if (cuentaService.exists(id)) {
            cuentaService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleException(Exception ex) {
        return ResponseUtil.badRequest(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Cuenta>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }

}
