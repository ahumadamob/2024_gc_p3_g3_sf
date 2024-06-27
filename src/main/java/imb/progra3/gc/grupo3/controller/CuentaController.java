package imb.progra3.gc.grupo3.controller;

import imb.progra3.gc.grupo3.entity.Cuenta;
import imb.progra3.gc.grupo3.service.ICuentaService;
import imb.progra3.gc.grupo3.util.APIResponse;
import imb.progra3.gc.grupo3.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;
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
    public ResponseEntity<APIResponse<List<Cuenta>>> getAllCuentas() {
        List<Cuenta> cuentas = cuentaService.findAll();
        return cuentas.isEmpty() ? ResponseUtil.notFound("No se encontraron cuentas") :
                ResponseUtil.success(cuentas);
    }



    @GetMapping("{id}")
    public ResponseEntity<APIResponse<Cuenta>> getCuentaById(@PathVariable("id") Long id){
        return cuentaService.exists(id) ? ResponseUtil.success(cuentaService.findById(id)) :
                ResponseUtil.notFound("No se encontró cuenta con id {0}", id);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Cuenta>> createCuenta(@RequestBody Cuenta cuenta){
        return cuentaService.exists(cuenta.getId()) ?
                ResponseUtil.badRequest("Ya existe una cuenta con id {0}", cuenta.getId()) :
                ResponseUtil.success(cuentaService.save(cuenta));
    }

    @PutMapping
    public ResponseEntity<APIResponse<Cuenta>> updateCuenta(@RequestBody Cuenta cuenta){
        return cuentaService.exists(cuenta.getId()) ? ResponseUtil.success(cuenta) :
                ResponseUtil.badRequest("No existe una cuenta con id {0}", cuenta.getId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Cuenta>> deleteCuenta(@PathVariable("id") Long id){
        if(cuentaService.exists(id)) {
            cuentaService.delete(id);
            return ResponseUtil.successDeleted("Se eliminó la cuenta con el id {0}", id);
        } else {
            return ResponseUtil.badRequest("No se encontró la cuenta con el id {0}", id);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Cuenta>> handleException(Exception ex) {
        return ResponseUtil.badRequest(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Cuenta>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
}
