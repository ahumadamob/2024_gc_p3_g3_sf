package imb.progra3.gc.grupo3.service.jpa;

import imb.progra3.gc.grupo3.dto.CuentaDTO;
import imb.progra3.gc.grupo3.entity.Cuenta;
import imb.progra3.gc.grupo3.repository.ClienteRepository;
import imb.progra3.gc.grupo3.repository.CuentaRepository;
import imb.progra3.gc.grupo3.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para crear una cuenta
    @Override
    public Cuenta crearCuenta(CuentaDTO cuentaDTO) {
        // Realizar todas las validaciones antes de crear la cuenta
        validarSaldo(cuentaDTO.getSaldo());
        validarTipoCuenta(cuentaDTO.getTipoCuenta());
        validarNumeroCuenta(cuentaDTO.getNumeroCuenta());
        validarExistenciaCliente(cuentaDTO.getIdCliente());

        // Crear una nueva cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldo(cuentaDTO.getSaldo());
        cuenta.setFechaApertura(LocalDate.now());
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTitular(cuentaDTO.getTitular());
        cuenta.setCliente(clienteRepository.findById(cuentaDTO.getIdCliente()).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado")));

        // Guardar la cuenta y devolverla
        return cuentaRepository.save(cuenta);
    }

    // Validación de saldo (debe ser mayor que cero)
    private void validarSaldo(Double saldo) {
        if (saldo <= 0) {
            throw new IllegalArgumentException("El saldo debe ser mayor que cero.");
        }
    }

    // Validación de tipo de cuenta (debe ser Ahorro o Corriente)
    private void validarTipoCuenta(String tipoCuenta) {
        if (!List.of("Ahorro", "Corriente").contains(tipoCuenta)) {
            throw new IllegalArgumentException("El tipo de cuenta debe ser 'Ahorro' o 'Corriente'.");
        }
    }

    // Validación del número de cuenta (no puede existir previamente)
    private void validarNumeroCuenta(String numeroCuenta) {
        if (cuentaRepository.existsByNumeroCuenta(numeroCuenta)) {
            throw new IllegalArgumentException("El número de cuenta ya existe.");
        }
    }

    // Validación de existencia del cliente
    private void validarExistenciaCliente(Long idCliente) {
        if (!clienteRepository.existsById(idCliente)) {
            throw new IllegalArgumentException("El cliente con id " + idCliente + " no existe.");
        }
    }

    // Obtener todas las cuentas
    @Override
    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }

    // Buscar cuenta por ID
    @Override
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    // Guardar una cuenta
    @Override
    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    // Eliminar una cuenta
    @Override
    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }

    // Verificar si una cuenta existe por ID
    @Override
    public boolean exists(Long id) {
        return cuentaRepository.existsById(id);
    }

    @Override
    public Cuenta getById(Long id) {
        return null;
    }

    // Actualizar el saldo de una cuenta
    @Override
    public boolean actualizarSaldo(Long id, Double nuevoSaldo) {
        if (nuevoSaldo == null || nuevoSaldo < 0) {
            return false;
        }
        return cuentaRepository.findById(id).map(cuenta -> {
            cuenta.setSaldo(nuevoSaldo);
            cuentaRepository.save(cuenta);
            return true;
        }).orElse(false);
    }
}
