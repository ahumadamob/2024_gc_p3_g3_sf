package service;

import java.util.List;

import entity.Ubicacion;

public interface IUbicacionService {
	List<Ubicacion> getAll();
	Ubicacion getById(Long id);
	Ubicacion save(Ubicacion ubicacion);
	void delete(Long id);
	boolean exists(Long id);
}
