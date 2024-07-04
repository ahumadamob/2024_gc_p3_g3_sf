package grupo3.demo.service;

import java.util.List;

import grupo3.demo.entity.Ubicacion;

public interface IUbicacionService {
	List<Ubicacion> getAll();
	Ubicacion getById(Long id);
	Ubicacion save(Ubicacion ubicacion);
	void delete(Long id);
	boolean exists(Long id);
}
