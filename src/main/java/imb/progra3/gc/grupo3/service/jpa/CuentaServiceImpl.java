package imb.progra3.gc.grupo3.service.jpa;

import imb.progra3.gc.grupo3.entity.Cuenta;
import imb.progra3.gc.grupo3.repository.CuentaRepository;
import imb.progra3.gc.grupo3.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;



    @Override
    public List<Cuenta> getAll() {
        return null;
    }

    @Override
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
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


    public boolean cuentaExistePorId(Long id) {
        return cuentaRepository.existsById(id);
    }


    public boolean cuentaExistePorNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.existsByNumeroCuenta(numeroCuenta);
    }
	@Override
	public Cuenta getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
