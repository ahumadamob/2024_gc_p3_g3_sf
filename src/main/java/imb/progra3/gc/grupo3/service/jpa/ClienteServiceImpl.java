package imb.progra3.gc.grupo3.service.jpa;

import imb.progra3.gc.grupo3.entity.Cliente;
import imb.progra3.gc.grupo3.repository.ClienteRepository;
import imb.progra3.gc.grupo3.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return clienteRepository.existsById(id);
    }

    @Override
    public List<Cliente> findClientesByEdad(int edad) {
        return clienteRepository.findByEdadGreaterThan(edad);
    }
    @Override
    public Cliente updateDireccion(Long id, String nuevaDireccion) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setDireccion(nuevaDireccion);
            return clienteRepository.save(cliente);
        }
        return null;
    }

}
