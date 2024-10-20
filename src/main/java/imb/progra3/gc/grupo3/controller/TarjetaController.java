package imb.progra3.gc.grupo3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import imb.progra3.gc.grupo3.entity.Tarjeta;
import imb.progra3.gc.grupo3.service.ITarjetaService;
import imb.progra3.gc.grupo3.util.APIResponse;
import imb.progra3.gc.grupo3.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private ITarjetaService tarjetaService;

    @GetMapping
	public ResponseEntity<APIResponse<List<Tarjeta>>> getAllTarjeta() {
		List<Tarjeta> tarjeta = tarjetaService.findAll();
		return 	tarjeta.isEmpty()? ResponseUtil.notFound("No se encontraron tarjeta") :
				ResponseUtil.success(tarjeta);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<Tarjeta>> getTarjetaById(@PathVariable("id") Long id){
		return 	tarjetaService.exists(id)? ResponseUtil.success(tarjetaService.findById(id)):
				ResponseUtil.notFound("No se encontró transaccion con id {0}", id);
	}	
	
	@PostMapping
	public ResponseEntity<APIResponse<Tarjeta>> createTarjeta(@RequestBody Tarjeta tarjeta){
		return 	tarjetaService.exists(tarjeta.getId())? ResponseUtil.badRequest("Ya existe una transacción con id {0}", tarjeta.getId()):
				ResponseUtil.success(tarjetaService.save(tarjeta));
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Tarjeta>> updateTarjeta(@RequestBody Tarjeta tarjeta){
		return 	tarjetaService.exists(tarjeta.getId())? ResponseUtil.success(tarjeta):
				ResponseUtil.badRequest("No existe un projecto con id {0}", tarjeta.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Object>> deleteTarjeta(@PathVariable("id") Long id){
		if(tarjetaService.exists(id)) {
			tarjetaService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó la transacción con el id {0}", id);
		}else {
			return ResponseUtil.badRequest("No se encontró la transacción con el id {0}", id);
		}		
	}
	
	@GetMapping("/{numeroTarjeta}")
    public List<Tarjeta> findBynumeroTarjeta(@PathVariable String numeroTarjeta) {
        return tarjetaService.findBynumeroTarjeta(numeroTarjeta);
    }
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleException(Exception ex) {    	
    	return ResponseUtil.badRequest(ex.getMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Tarjeta>> handleConstraintViolationException(ConstraintViolationException ex) {
    	return ResponseUtil.handleConstraintException(ex);
    }
}
