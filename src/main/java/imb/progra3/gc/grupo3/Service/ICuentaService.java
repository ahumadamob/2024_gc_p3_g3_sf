package  imb.progra3.gc.grupo3.Service;

import java.util.List;

public interface ICuentaService {
     public List<Cuenta> getAll();
    public Cuenta getById(Long id);
    public Cuenta save(Cuenta cuenta);
    public void delete(Long id);
    public boolean exists(Long id);
}