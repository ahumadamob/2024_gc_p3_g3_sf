package tarjetarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Tarjeta;


public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
}
