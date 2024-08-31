package imb.progra3.gc.grupo3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import imb.progra3.gc.grupo3.entity.Tarjeta;
import imb.progra3.gc.grupo3.service.ITarjetaService;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private ITarjetaService tarjetaService;

    @GetMapping
    public List<Tarjeta> getAllTarjetas() {
        return tarjetaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> getTarjetaById(@PathVariable Long id) {
        Tarjeta tarjeta = tarjetaService.getById(id);
        if (tarjeta != null) {
            return ResponseEntity.ok(tarjeta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Tarjeta createTarjeta(@RequestBody Tarjeta tarjeta) {
        return tarjetaService.save(tarjeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarjeta> updateTarjeta(@PathVariable Long id, @RequestBody Tarjeta tarjetaDetails) {
        Tarjeta tarjeta = tarjetaService.getById(id);
        if (tarjeta != null) {
            tarjeta.setNumeroTarjeta(tarjetaDetails.getNumeroTarjeta());
            tarjeta.setFechaVencimiento(tarjetaDetails.getFechaVencimiento());
            tarjeta.setEstado(tarjetaDetails.getEstado());
            tarjeta.setIdCuenta(tarjetaDetails.getIdCuenta());
            Tarjeta updatedTarjeta = tarjetaService.save(tarjeta);
            return ResponseEntity.ok(updatedTarjeta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarjeta(@PathVariable Long id) {
        if (tarjetaService.exists(id)) {
            tarjetaService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
