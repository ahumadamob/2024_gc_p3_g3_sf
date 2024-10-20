package imb.progra3.gc.grupo3.service;

import imb.progra3.gc.grupo3.entity.Ubicacion;
import java.util.List;

public interface IUbicacionService {
	List<Ubicacion> getAll();
	Ubicacion getById(Long id);
	Ubicacion save(Ubicacion ubicacion);
	void delete(Long id);
	boolean exists(Long id);
}