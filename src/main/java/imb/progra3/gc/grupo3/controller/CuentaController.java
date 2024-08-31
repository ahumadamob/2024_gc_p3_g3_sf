package imb.progra3.gc.grupo3.controller;
import imb.progra3.gc.grupo3.entity.Cuenta;
import imb.progra3.gc.grupo3.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/cuenta")
public class CuentaController {
    @Autowired
    private ICuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getAllTarjetas() {
        return cuentaService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cuenta> getTarjetaById(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.getById(id);
        if (cuenta != null) {
            return ResponseEntity.ok(cuenta);
        } else {
            return ResponseEntity.notFound().build();
        }
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

}
