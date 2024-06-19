package imb.progra3.gc.grupo3.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import imb.progra3.gc.grupo3.entity.Transaccion;
import imb.progra3.gc.grupo3.repository.TransaccionRepository;
import imb.progra3.gc.grupo3.service.ITransaccionService;

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
		return id == null ? false : repo.existsById(id);
	}

	@Override
	public Transaccion save(Transaccion transaccion) {
		return repo.save(transaccion);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
