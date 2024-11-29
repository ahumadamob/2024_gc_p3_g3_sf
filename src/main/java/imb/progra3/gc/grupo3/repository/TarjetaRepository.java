package imb.progra3.gc.grupo3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import imb.progra3.gc.grupo3.entity.Tarjeta;


	public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
	    
		List<Tarjeta> findBynumeroTarjeta(String numeroTarjeta);
		
		@Query("SELECT COUNT(t) FROM Tarjeta t WHERE t.estado = :estado AND t.cuenta.id = :idCuenta")
		long countByEstadoAndCuentaId(@Param("estado") String estado, @Param("idCuenta") Long idCuenta);

	}
