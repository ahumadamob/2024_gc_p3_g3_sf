package imb.progra3.gc.grupo3.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import imb.progra3.gc.grupo3.entity.Transaccion;
import imb.progra3.gc.grupo3.repository.TransaccionRepository;
import imb.progra3.gc.grupo3.service.ITransaccionService;
// nuevo import
import java.util.NoSuchElementException;

@Service
public class TransaccionServiceImpl implements ITransaccionService {

    @Autowired
    private TransaccionRepository repo;

    @Override
    public List<Transaccion> findAll() {
        return repo.findAll(); 
    }

    @Override
    public Transaccion findById(Long id) {
        return repo.findById(id).orElse(null); 
    }

    @Override
    public boolean exists(Long id) {
        return repo.existsById(id); 
    }

    @Override
    public Transaccion save(Transaccion transaccion) {
        return repo.save(transaccion);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Transaccion> findByTipo(String tipo) {
        return repo.findByTipoTransaccion(tipo);
    }

    @Override
    public Transaccion getById(Long id) {
        return findById(id);
    }
    
    //nuevo metodo
    @Override
    public void updateEstadoTransaccion(Long id, String estado) {
        Transaccion transaccion = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transacción no encontrada."));

        if (!esEstadoValido(estado)) {
            throw new IllegalArgumentException("Estado inválido. Debe ser 'Pendiente', 'Completada', o 'Cancelada'.");
        }

        transaccion.setEstado(estado);
        repo.save(transaccion);
    }

    private boolean esEstadoValido(String estado) {
        return estado.equals("Pendiente") || estado.equals("Completada") || estado.equals("Cancelada");
    }

}