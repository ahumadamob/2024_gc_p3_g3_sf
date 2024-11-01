package imb.progra3.gc.grupo3.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import imb.progra3.gc.grupo3.entity.Ubicacion;
import imb.progra3.gc.grupo3.repository.UbicacionRepository;
import imb.progra3.gc.grupo3.service.IUbicacionService;
import jakarta.persistence.EntityNotFoundException;


@Service
public class UbicacionServiceImpl implements IUbicacionService{
	@Autowired
	private UbicacionRepository ubicacionRepository;
	
	 @Override
	    public List<Ubicacion> getAll() {
	        return ubicacionRepository.findAll();
	    }

	    @Override
	    public Ubicacion getById(Long id) {
	    	Optional<Ubicacion> optionalUbicacion = ubicacionRepository.findById(id);
	        return optionalUbicacion.orElse(null);
	    }

	    @Override
	    public Ubicacion save(Ubicacion ubicacion) {
	        return ubicacionRepository.save(ubicacion);
	    }

	    @Override
	    public void delete(Long id) {
	        ubicacionRepository.deleteById(id);
	    }

	    @Override
	    public boolean exists(Long id) {
	        return ubicacionRepository.existsById(id);
	    }
	 // Implementación del método mágico
	    @Override
	    public List<Ubicacion> findByCiudad(String ciudad) {
	        return ubicacionRepository.findByCiudad(ciudad);
	    }
	    @Override
	    public void updateDescripcion(Long id, String nuevaDescripcion) {
	        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);
	        
	        if (ubicacionOptional.isPresent()) {
	            Ubicacion ubicacion = ubicacionOptional.get();
	            ubicacion.setDescripcion(nuevaDescripcion);  // Actualiza solo la descripción
	            ubicacionRepository.save(ubicacion);  // Guarda los cambios
	        } else {
	            throw new EntityNotFoundException("Ubicación no encontrada para el ID: " + id);
	        }
	    }

}
