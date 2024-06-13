package  imb.progra3.gc.grupo3.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import imb.progra3.gc.grupo3.entity.Cuenta

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {


}