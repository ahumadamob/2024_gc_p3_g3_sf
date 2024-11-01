package imb.progra3.gc.grupo3.controller;

import imb.progra3.gc.grupo3.entity.Ubicacion;
import imb.progra3.gc.grupo3.service.IUbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/ubicaciones")

public class UbicacionController {
    @Autowired
    private IUbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<List<Ubicacion>> getAll() {
        List<Ubicacion> ubicaciones = ubicacionService.getAll();
        return ResponseEntity.ok(ubicaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getById(@PathVariable("id") Long id) {
        Ubicacion ubicacion = ubicacionService.getById(id);
    	return ubicacion != null ? ResponseEntity.ok(ubicacion):ResponseEntity.notFound().build();
    }
    
 // Método mágico
    @GetMapping("/filter/{ciudad}")
    public ResponseEntity<List<Ubicacion>> filterUbicaciones(@PathVariable("ciudad") String ciudad) {
        List<Ubicacion> ubicaciones = ubicacionService.findByCiudad(ciudad);
        return ubicaciones.isEmpty() 
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) 
            : ResponseEntity.ok(ubicaciones);
    }    
    
    @PostMapping("/ubicaciones")
    public ResponseEntity<Ubicacion> save(@RequestBody Ubicacion ubicacion) {
        Ubicacion savedUbicacion = ubicacionService.save(ubicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUbicacion);
    }

    @PutMapping("/ubicaciones/{id}")
    public ResponseEntity<Ubicacion> update(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
        if (ubicacionService.exists(id)) {
        	ubicacion.setId(id);
            Ubicacion updatedUbicacion = ubicacionService.save(ubicacion);   
            return ResponseEntity.ok(updatedUbicacion);
        }else { 
        	return ResponseEntity.notFound().build();
        }
    }
   /* @PutMapping("/ubicaciones/{id}/descripcion")
    public ResponseEntity<String> updateDescripcion(@PathVariable("id") Long id, @RequestBody Map<String, String> payload) {
        String nuevaDescripcion = payload.get("descripcion");
        if (nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Se requiere una descripción válida.");
        }
        
        if (!ubicacionService.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicación no encontrada.");
        }
        
        ubicacionService.updateDescripcion(id, nuevaDescripcion);
        return ResponseEntity.ok("Descripción actualizada con éxito.");
    }

    @DeleteMapping("/ubicacion/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ubicacionService.exists(id)) {
           ubicacionService.delete(id);
           return ResponseEntity.noContent().build();
    } else {
    	return ResponseEntity.notFound().build();
    }
}*/
    @PutMapping("/ubicaciones/{id}/descripcion")
    public ResponseEntity<String> updateDescripcion(@PathVariable("id") Long id, @RequestBody Map<String, String> payload) {
        // Verificar si la ubicación existe primero
        if (!ubicacionService.exists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicación no encontrada.");
        }
        
        String nuevaDescripcion = payload.get("descripcion");
        
        // Validar que la descripción no sea nula o vacía
        if (nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Se requiere una descripción válida.");
        }
        
        ubicacionService.updateDescripcion(id, nuevaDescripcion);
        return ResponseEntity.ok("Descripción actualizada con éxito.");
    }

}