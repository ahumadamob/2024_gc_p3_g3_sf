package controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import imb.progra3.gc.grupo3.entity.Transaccion;
import jakarta.validation.ConstraintViolationException;
import service.ITransaccionService;
import util.APIResponse;
import util.ResponseUtil;

@RestController
@RequestMapping(path="/api/v1/transaccion")
public class TransaccionController {
	
	@Autowired
	private ITransaccionService transaccionService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Transaccion>>> getAllTransaccion() {
		List<Transaccion> transaccion = transaccionService.findAll();
		return 	transaccion.isEmpty()? ResponseUtil.notFound("No se encontraron transacciones") :
				ResponseUtil.success(transaccion);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<Transaccion>> getTransaccionById(@PathVariable("id") Long id){
		return 	transaccionService.exists(id)? ResponseUtil.success(transaccionService.findById(id)):
				ResponseUtil.notFound("No se encontró transaccion con id {0}", id);
	}	
	
	@PostMapping
	public ResponseEntity<APIResponse<Transaccion>> createProject(@RequestBody Transaccion transaccion){
		return 	transaccionService.exists(transaccion.getId())? ResponseUtil.badRequest("Ya existe una transacción con id {0}", transaccion.getId()):
				ResponseUtil.success(transaccionService.save(transaccion));
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Transaccion>> updateProject(@RequestBody Transaccion transaccion){
		return 	transaccionService.exists(transaccion.getId())? ResponseUtil.success(transaccion):
				ResponseUtil.badRequest("No existe un projecto con id {0}", transaccion.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Transaccion>> deleteProject(@PathVariable("id") Long id){
		if(transaccionService.exists(id)) {
			transaccionService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó la transacción con el id {0}", id);
		}else {
			return ResponseUtil.badRequest("No se encontró la transacción con el id {0}", id);
		}		
	}
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Transaccion>> handleException(Exception ex) {    	
    	return ResponseUtil.badRequest(ex.getMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Transaccion>> handleConstraintViolationException(ConstraintViolationException ex) {
    	return ResponseUtil.handleConstraintException(ex);
    }

}
