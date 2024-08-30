package imb.progra3.gc.grupo3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import imb.progra3.gc.grupo3.entity.Tarjeta;
import imb.progra3.gc.grupo3.entity.Transaccion;
import imb.progra3.gc.grupo3.service.ITransaccionService;
//import jakarta.validation.ConstraintViolationException;
//import util.APIResponse;
//import util.ResponseUtil;

@RestController
@RequestMapping(path="api/transaccion")
public class TransaccionController {
	
	@Autowired
	private ITransaccionService transaccionService;
	
	@GetMapping
	public List<Transaccion> getAllTransaccion() {
        return transaccionService.getAll();
    }

	@GetMapping("{id}")
	public ResponseEntity<Transaccion> getTransaccionById(@PathVariable Long id) {
		Transaccion transaccion = transaccionService.getById(id);
        if (transaccion != null) {
            return ResponseEntity.ok(transaccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@PostMapping
	public Transaccion createTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.save(transaccion);
    }

	@PutMapping
	 public ResponseEntity<Transaccion> updateTransaccion(@PathVariable Long id, @RequestBody Transaccion transaccionDetails) {
		Transaccion transaccion = transaccionService.getById(id);
        if (transaccion != null) {
        	transaccion.setFechaHora(transaccionDetails.getFechaHora());
        	transaccion.setId(transaccionDetails.getId());
        	transaccion.setIdCuenta(transaccionDetails.getIdCuenta());
        	transaccion.setMonto(transaccionDetails.getMonto());
        	transaccion.setTipoTransaccion(transaccionDetails.getTipoTransaccion());
        	Transaccion updatedTransaccion = transaccionService.save(transaccion);
            return ResponseEntity.ok(updatedTransaccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteTransaccion(@PathVariable Long id) {
        if (transaccionService.exists(id)) {
        	transaccionService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
		}

    //@ExceptionHandler(Exception.class)
    //public ResponseEntity<APIResponse<Transaccion>> handleException(Exception ex) {
    	//return ResponseUtil.badRequest(ex.getMessage());
    //}

    //@ExceptionHandler(ConstraintViolationException.class)
    //public ResponseEntity<APIResponse<Transaccion>> handleConstraintViolationException(ConstraintViolationException ex) {
    //	return ResponseUtil.handleConstraintException(ex);
    //}

}
