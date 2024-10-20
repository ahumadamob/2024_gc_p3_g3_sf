package imb.progra3.gc.grupo3.repository;

import imb.progra3.gc.grupo3.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    boolean existsById(Long id);


    boolean existsByNumeroCuenta(String numeroCuenta);
}
