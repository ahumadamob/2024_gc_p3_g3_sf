package  imb.progra3.gc.grupo3.Service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import imb.progra3.gc.grupo3.entity.Cuenta;
import repository.CuentaRepository;
import service.ICuentaService;


@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta getById(Long id) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        return cuenta.orElse(null);
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return cuentaRepository.existsById(id);
    }
}