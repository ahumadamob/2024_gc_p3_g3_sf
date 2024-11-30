package imb.progra3.gc.grupo3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import imb.progra3.gc.grupo3.entity.Ubicacion;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long>{

	List<Ubicacion> findByCiudad(String ciudad);
	boolean existsByDireccionAndCiudad(String direccion, String ciudad);
	
}
