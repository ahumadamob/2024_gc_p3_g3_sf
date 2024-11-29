package imb.progra3.gc.grupo3.service;

import imb.progra3.gc.grupo3.entity.Ubicacion;
import java.util.List;

public interface IUbicacionService {
	List<Ubicacion> getAll();
	Ubicacion getById(Long id);
	//Ubicacion save(Ubicacion ubicacion);
	void delete(Long id);
	boolean exists(Long id);
	List<Ubicacion> findByCiudad(String ciudad);
	void updateDescripcion(Long id, String nuevaDescripcion);

	public default Ubicacion save(Ubicacion ubicacion) {
		IUbicacionService ubicacionRepository = null;
		if (ubicacionRepository.existsByDireccionAndCiudad(ubicacion.getDireccion(), ubicacion.getCiudad())) {
			throw new IllegalArgumentException("Ya existe una ubicación registrada con la misma dirección y ciudad.");
		}
		return ubicacionRepository.save(ubicacion);
	}

	// Método para verificar si existe una ubicación con la misma dirección y ciudad
	public default boolean existsByDireccionAndCiudad(String direccion, String ciudad) {
		IUbicacionService ubicacionRepository = null;
		return ubicacionRepository.existsByDireccionAndCiudad(direccion, ciudad);
	}

}