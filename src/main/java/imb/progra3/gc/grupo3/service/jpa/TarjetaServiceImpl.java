package imb.progra3.gc.grupo3.service.jpa;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import imb.progra3.gc.grupo3.entity.Tarjeta;
import imb.progra3.gc.grupo3.repository.TarjetaRepository;
import imb.progra3.gc.grupo3.service.ITarjetaService;

@Service
public class TarjetaServiceImpl implements ITarjetaService {

	@Autowired
	private TarjetaRepository repo;
	
	@Override
	public List<Tarjeta> findAll() {
		return repo.findAll();
	}

	@Override
	public Tarjeta findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public boolean exists(Long id) {
		return id == null ? false : repo.existsById(id);
	}

	@Override
	public Tarjeta save(Tarjeta tarjeta) {
		return repo.save(tarjeta);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	@Override
	public List<Tarjeta> findBynumeroTarjeta(String numeroTarjeta) {
		return repo.findBynumeroTarjeta(numeroTarjeta);
	}
	
	@Override
	public boolean bloquearTarjeta(Long id) {
	    Optional<Tarjeta> tarjetaOptional = repo.findById(id);
	       if (tarjetaOptional.isPresent()) {
	           Tarjeta tarjeta = tarjetaOptional.get();
	           tarjeta.setEstado("bloqueada");
	           repo.save(tarjeta);
	           return true;
	        }
	        return false;
	    }
	}