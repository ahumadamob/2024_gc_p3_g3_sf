package imb.progra3.gc.grupo3.service;

import java.util.List;
import imb.progra3.gc.grupo3.entity.Tarjeta;


public interface ITarjetaService {

    List<Tarjeta> getAll();
    Tarjeta getById(Long id);
    Tarjeta save(Tarjeta tarjeta);
    void delete(Long id);
    boolean exists(Long id);
}