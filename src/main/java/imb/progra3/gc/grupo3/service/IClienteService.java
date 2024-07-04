package imb.progra3.gc.grupo3.service;

import imb.progra3.gc.grupo3.entity.Cliente;
import java.util.List;


public interface IClienteService {
    List<Cliente> getAll();
    Cliente getById(Long id);
    Cliente save(Cliente cliente);
    void delete(Long id);
    boolean exists(Long id);
}
