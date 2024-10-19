package imb.progra3.gc.grupo3.service;

import imb.progra3.gc.grupo3.entity.Cuenta;

import java.util.List;

public interface ICuentaService {


    List<Cuenta> getAll();
    Cuenta findById(Long id);

    Cuenta save(Cuenta cuenta);
    void delete(Long id);
    boolean exists(Long id);
	Cuenta getById(Long id);



}
