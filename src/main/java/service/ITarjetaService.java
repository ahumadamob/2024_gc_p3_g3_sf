package service;

import java.util.List;
import entity.Tarjeta;

public interface ITarjetaService {

    List<Tarjeta> getAll();
    Tarjeta getById(Long id);
    Tarjeta save(Tarjeta tarjeta);
    void delete(Long id);
    boolean exists(Long id);
}