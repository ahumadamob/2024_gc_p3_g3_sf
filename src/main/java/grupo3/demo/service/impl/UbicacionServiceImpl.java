package service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import entity.Ubicacion;
import repository.UbicacionRepository;
import service.IUbicacionService;

@Service
public class UbicacionServiceImpl implements IUbicacionService{
	private final UbicacionRepository ubicacionRepository;
	public UbicacionServiceImpl(UbicacionRepository ubicacionRepository) {
		this.ubicacionRepository = ubicacionRepository;
	}
	
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
