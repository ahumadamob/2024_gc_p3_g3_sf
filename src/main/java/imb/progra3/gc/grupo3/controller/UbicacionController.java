package imb.progra3.gc.grupo3.controller;

import imb.progra3.gc.grupo3.dto.APIResponse;
import imb.progra3.gc.grupo3.dto.DescripcionDTO;
import imb.progra3.gc.grupo3.dto.UbicacionDTO;
import imb.progra3.gc.grupo3.entity.Ubicacion;
import imb.progra3.gc.grupo3.service.IUbicacionService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    
//   @PostMapping("/api/ubicaciones")
    @PostMapping
    public ResponseEntity<Ubicacion> save(@RequestBody Ubicacion ubicacion) {
        Ubicacion savedUbicacion = ubicacionService.save(ubicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUbicacion);
    }
    
    @PostMapping
    public ResponseEntity<Ubicacion> save(@Valid @RequestBody UbicacionDTO ubicacionDTO) {
        // Validamos las coordenadas geográficas
        if (ubicacionDTO.getLatitud() < -90 || ubicacionDTO.getLatitud() > 90) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("La latitud debe estar entre -90 y 90 grados.");
        }
        if (ubicacionDTO.getLongitud() < -180 || ubicacionDTO.getLongitud() > 180) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("La longitud debe estar entre -180 y 180 grados.");
        }

        // Crear la entidad Ubicacion a partir del DTO recibido
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setPais(ubicacionDTO.getPais());
        ubicacion.setCiudad(ubicacionDTO.getCiudad());
        ubicacion.setDireccion(ubicacionDTO.getDireccion());
        ubicacion.setDescripcion(ubicacionDTO.getDescripcion());
        ubicacion.setCodigoPostal(ubicacionDTO.getCodigoPostal());
        ubicacion.setLatitud(ubicacionDTO.getLatitud());  // Asignamos latitud validada
        ubicacion.setLongitud(ubicacionDTO.getLongitud());  // Asignamos longitud validada

        // Guardamos la ubicación
        Ubicacion savedUbicacion = ubicacionService.save(ubicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUbicacion);
    }

    

//    @PutMapping("/api/ubicaciones/{id}")
/*    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> update(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
        if (ubicacionService.exists(id)) {
        	ubicacion.setId(id);
            Ubicacion updatedUbicacion = ubicacionService.save(ubicacion);   
            return ResponseEntity.ok(updatedUbicacion);
        }else { 
        	return ResponseEntity.notFound().build();
        }
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UbicacionDTO ubicacionDTO) {
        if (ubicacionService.exists(id)) {
            // Validamos las coordenadas geográficas
            if (ubicacionDTO.getLatitud() < -90 || ubicacionDTO.getLatitud() > 90) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La latitud debe estar entre -90 y 90 grados.");
            }
            if (ubicacionDTO.getLongitud() < -180 || ubicacionDTO.getLongitud() > 180) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La longitud debe estar entre -180 y 180 grados.");
            }
            
            // Actualizamos la entidad con los datos del DTO
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setId(id);
            ubicacion.setPais(ubicacionDTO.getPais());
            ubicacion.setCiudad(ubicacionDTO.getCiudad());
            ubicacion.setDireccion(ubicacionDTO.getDireccion());
            ubicacion.setDescripcion(ubicacionDTO.getDescripcion());
            ubicacion.setCodigoPostal(ubicacionDTO.getCodigoPostal());
            ubicacion.setLatitud(ubicacionDTO.getLatitud());  // Aseguramos que la latitud esté validada
            ubicacion.setLongitud(ubicacionDTO.getLongitud());  // Aseguramos que la longitud esté validada

            Ubicacion updatedUbicacion = ubicacionService.save(ubicacion);
            return ResponseEntity.ok(updatedUbicacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}/descripcion")
    public ResponseEntity<APIResponse<String>> updateDescripcion(@PathVariable("id") Long id, @RequestBody DescripcionDTO descripcionDTO) {
        List<String> mensajes = new ArrayList<>();

        // Verificar si la ubicación existe primero
        if (!ubicacionService.exists(id)) {
            mensajes.add("Ubicación no encontrada");
            APIResponse<String> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), mensajes, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Validar que la descripción no sea nula o vacía
        String nuevaDescripcion = descripcionDTO.getDescripcion();
        if (nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()) {
            mensajes.add("Se requiere una descripción válida.");
            APIResponse<String> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), mensajes, null);
            return ResponseEntity.badRequest().body(response);
        }

        ubicacionService.updateDescripcion(id, nuevaDescripcion);
        mensajes.add("Descripción actualizada con éxito.");
        APIResponse<String> response = new APIResponse<>(HttpStatus.OK.value(), mensajes, nuevaDescripcion);
        return ResponseEntity.ok(response);
    }

}