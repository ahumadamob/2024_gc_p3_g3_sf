package grupo3.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo3.demo.entity.Ubicacion;
import grupo3.demo.repository.UbicacionRepository;
import grupo3.demo.service.IUbicacionService;

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
	        return ubicacionRepository.findById(id).orElse(null);
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
}
