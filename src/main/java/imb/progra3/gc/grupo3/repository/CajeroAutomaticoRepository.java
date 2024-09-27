package imb.progra3.gc.grupo3.repository;
import imb.progra3.gc.grupo3.entity.Cajeroautomatico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CajeroAutomaticoRepository extends JpaRepository<Cajeroautomatico, Long> {
    List<Cajeroautomatico> findByUbicacion(String ubicacion);

}
