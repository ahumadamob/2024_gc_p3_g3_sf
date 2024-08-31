package imb.progra3.gc.grupo3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.progra3.gc.grupo3.entity.Ubicacion;
import imb.progra3.gc.grupo3.service.IUbicacionService;


@RestController
@RequestMapping("api/ubicaciones")
public class UbicacionController {
	@Autowired
	private IUbicacionService ubicacionService;
	
	public UbicacionController(IUbicacionService ubicacionService) {
		this.ubicacionService = ubicacionService;
	}
	@GetMapping
    public List<Ubicacion> getAll() {
        return ubicacionService.getAll();
    }
	@GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getById(@PathVariable Long id) {
        Ubicacion ubicacion = ubicacionService.getById(id);
        if (ubicacion != null) {
            return ResponseEntity.ok(ubicacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	 @PostMapping
	    public Ubicacion create(@RequestBody Ubicacion ubicacion) {
	        return ubicacionService.save(ubicacion);
	    }
	 @PutMapping("/{id}")
	    public ResponseEntity<Ubicacion> update(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
	        if (ubicacionService.exists(id)) {
	            ubicacion.setId(id);
	            Ubicacion updatedUbicacion = ubicacionService.save(ubicacion);
	            return ResponseEntity.ok(updatedUbicacion);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        if (!ubicacionService.exists(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        ubicacionService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
}
