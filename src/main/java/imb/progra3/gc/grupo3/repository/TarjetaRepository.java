package imb.progra3.gc.grupo3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import imb.progra3.gc.grupo3.entity.Tarjeta;


	public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
	    
		List<Tarjeta> findBynumeroTarjeta(String numeroTarjeta);
	}
