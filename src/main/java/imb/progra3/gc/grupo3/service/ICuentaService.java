package imb.progra3.gc.grupo3.service;

import imb.progra3.gc.grupo3.dto.CuentaDTO;
import imb.progra3.gc.grupo3.entity.Cuenta;

import java.util.List;

public interface ICuentaService {


    static void validarSaldo(Double saldo) {
    }

    static void validarTipoCuenta(String tipoCuenta) {
    }

    static void validarExistenciaCliente(Long idCliente) {
    }

    static void validarNumeroCuentaUnico(String numeroCuenta) {
    }

    List<Cuenta> getAll();
    Cuenta findById(Long id);

    Cuenta save(Cuenta cuenta);
    void delete(Long id);
    boolean exists(Long id);
	Cuenta getById(Long id);

    boolean actualizarSaldo(Long id, Double nuevoSaldo);
    Cuenta crearCuenta(CuentaDTO cuentaDTO);

}
