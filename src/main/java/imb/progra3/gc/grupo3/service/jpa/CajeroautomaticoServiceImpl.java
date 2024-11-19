package imb.progra3.gc.grupo3.service.jpa;
import java.util.List;

import imb.progra3.gc.grupo3.entity.Cajeroautomatico;
import imb.progra3.gc.grupo3.exception.ResourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.progra3.gc.grupo3.repository.CajeroAutomaticoRepository;
import imb.progra3.gc.grupo3.service.ICajeroAutomaticoService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CajeroautomaticoServiceImpl implements ICajeroAutomaticoService {
    @Autowired
    private CajeroAutomaticoRepository repository;

    @Override

    public List<Cajeroautomatico> findAll() {
        return repository.findAll();
    }

    @Override

    public Cajeroautomatico findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override

    public Cajeroautomatico save(Cajeroautomatico cajeroautomatico) {
        return repository.save(cajeroautomatico);
    }

    @Override

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cajeroautomatico> findByUbicacion(String ubicacion) {
        return repository.findByUbicacion(ubicacion);
    }

    @Override
    @Transactional
    public void actualizarUbicacion(Long id, String nuevaUbicacion) {
        Cajeroautomatico cajero = repository.findById(id)
                .orElseThrow();

        cajero.setUbicacion(nuevaUbicacion);
        repository.save(cajero);
    }
}
