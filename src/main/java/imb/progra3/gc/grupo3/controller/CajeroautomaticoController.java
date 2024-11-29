package imb.progra3.gc.grupo3.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import imb.progra3.gc.grupo3.entity.Cajeroautomatico;
import imb.progra3.gc.grupo3.util.APIResponse;
import org.springframework.http.HttpStatus;
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
import imb.progra3.gc.grupo3.service.ICajeroAutomaticoService;

@RestController
@RequestMapping(path = "/api/Cajeroautomatico")

public class CajeroautomaticoController {
    @Autowired
    private ICajeroAutomaticoService service;

    @GetMapping
    public List<Cajeroautomatico> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cajeroautomatico> getById(@PathVariable Long id) {
        Cajeroautomatico cajeroautomatico = service.findById(id);
        if (cajeroautomatico != null) {
            return new ResponseEntity<>(cajeroautomatico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Cajeroautomatico cajeroautomatico) {
        @SuppressWarnings("unused")
        Cajeroautomatico createdCajeroautomatico = service.save(cajeroautomatico);
        return new ResponseEntity<>("El cajero fue creado con exito", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Cajeroautomatico cajeroautomatico) {
        Cajeroautomatico existingCajeroautomatico = service.findById(id);
        if (existingCajeroautomatico != null) {
            cajeroautomatico.setId(id);
            @SuppressWarnings("unused")
            Cajeroautomatico updatedCajeroautomatico = service.save(cajeroautomatico);
            return new ResponseEntity<>("El cajero fue actualizado con exito.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró cajero para actualizar.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/ubicacion")
    public ResponseEntity<APIResponse<Object>> updateUbicacion(@PathVariable Long id, @RequestBody Cajeroautomatico cajeroautomaticoRequest) {
        try {
            Cajeroautomatico existingCajeroautomatico = service.findById(id);

            if (existingCajeroautomatico == null) {
                APIResponse<Object> response = new APIResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        Collections.singletonList("No se encontró cajero para actualizar la ubicación."),
                        null
                );
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            existingCajeroautomatico.setUbicacion(cajeroautomaticoRequest.getUbicacion());
            Cajeroautomatico updatedCajeroautomatico = service.save(existingCajeroautomatico);

            APIResponse<Object> response = new APIResponse<>(
                    HttpStatus.OK.value(),
                    Collections.singletonList("La ubicación del cajero fue actualizada con éxito."),
                    updatedCajeroautomatico
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            APIResponse<Object> errorResponse = new APIResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    Arrays.asList("Error al actualizar la ubicación", e.getMessage()),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Cajeroautomatico existingCajeroautomatico = service.findById(id);
        if (existingCajeroautomatico != null) {
            service.delete(id);
            return new ResponseEntity<>("El cajero fue eliminado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró cajero para eliminar.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ubicacion/{ubicacion}")
    public ResponseEntity<List<Cajeroautomatico>> findByUbicacion(@PathVariable String ubicacion) {
        List<Cajeroautomatico> cajeroautomatico = (List<Cajeroautomatico>) service.findByUbicacion(ubicacion);
        if (!cajeroautomatico.isEmpty()) {
            return ResponseEntity.ok(cajeroautomatico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}