package imb.progra3.gc.grupo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import imb.progra3.gc.grupo3.entity.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findByTipoTransaccion(String tipo);
}
