package imb.progra3.gc.grupo3.repository;

import imb.progra3.gc.grupo3.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {




    boolean existsByNumeroCuenta(String numeroCuenta);
    boolean existsById(Long id);

    // Método opcional para obtener cuentas por tipo
    List<Cuenta> findByTipoCuenta(String tipoCuenta);





}
