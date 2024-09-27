package imb.progra3.gc.grupo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import imb.progra3.gc.grupo3.entity.Ubicacion;
import imb.progra3.gc.grupo3.service.UbicacionService;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    // Obtener todas las ubicaciones
    @GetMapping
    public ResponseEntity<List<Ubicacion>> getUbicaciones() {
        List<Ubicacion> ubicaciones = ubicacionService.getUbicaciones();
        return new ResponseEntity<>(ubicaciones, HttpStatus.OK);
    }

    // Obtener una ubicación por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getUbicacionById(@PathVariable Long id) {
        return ubicacionService.getUbicacionById(id)
            .map(ubicacion -> new ResponseEntity<>(ubicacion, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Guardar una nueva ubicación
    @PostMapping
    public ResponseEntity<Ubicacion> saveUbicacion(@RequestBody Ubicacion ubicacion) {
        Ubicacion nuevaUbicacion = ubicacionService.saveUbicacion(ubicacion);
        return new ResponseEntity<>(nuevaUbicacion, HttpStatus.CREATED);
    }

    // Actualizar una ubicación existente
    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> updateUbicacion(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
        return ubicacionService.updateUbicacion(id, ubicacion)
            .map(updatedUbicacion -> new ResponseEntity<>(updatedUbicacion, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar una ubicación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Long id) {
        if (ubicacionService.deleteUbicacion(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}