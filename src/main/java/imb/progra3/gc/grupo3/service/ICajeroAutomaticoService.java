package imb.progra3.gc.grupo3.service;

import imb.progra3.gc.grupo3.entity.Cajeroautomatico;

import java.util.List;

public interface ICajeroAutomaticoService {
    List<Cajeroautomatico> findAll();
    Cajeroautomatico findById(Long id);
    Cajeroautomatico save(Cajeroautomatico cajeroautomatico);
    void delete(Long id);
    List<Cajeroautomatico> findByUbicacion(String ubicacion);
    }
