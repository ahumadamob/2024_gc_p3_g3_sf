package imb.progra3.gc.grupo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.progra3.gc.grupo3.entity.Ubicacion;
import imb.progra3.gc.grupo3.repository.UbicacionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Ubicacion> getUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> getUbicacionById(Long id) {
        return ubicacionRepository.findById(id);
    }

    public Ubicacion saveUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public Optional<Ubicacion> updateUbicacion(Long id, Ubicacion ubicacion) {
        return ubicacionRepository.findById(id).map(existingUbicacion -> {
            existingUbicacion.setNombre(ubicacion.getNombre());
            existingUbicacion.setLatitud1(ubicacion.getLatitud1());
            existingUbicacion.setLatitud(ubicacion.getLongitud());
            return ubicacionRepository.save(existingUbicacion);
        });
    }

    public boolean deleteUbicacion(Long id) {
        if (ubicacionRepository.existsById(id)) {
            ubicacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}