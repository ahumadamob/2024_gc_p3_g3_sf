package imb.progra3.gc.grupo3.repository;

import imb.progra3.gc.grupo3.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByEdadGreaterThan(int edad);
}
