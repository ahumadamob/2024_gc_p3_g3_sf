package imb.progra3.gc.grupo3.service.jpa;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import imb.progra3.gc.grupo3.dto.TarjetaDTO;
import imb.progra3.gc.grupo3.entity.Cuenta;
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
	
	@Override 
	public Tarjeta crearTarjeta(TarjetaDTO tarjeta) {
		 
	        if (tarjeta.getFechaVencimiento().isBefore(LocalDate.now())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de vencimiento no puede ser anterior a la fecha actual.");
	        }

	        long tarjetasActivas = repo.countByEstadoAndCuentaId("activa", tarjeta.getIdCuenta());
	        if (tarjetasActivas >= 3) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pueden crear más de 3 tarjetas activas para esta cuenta.");
	        }

	        Tarjeta nuevaTarjeta = new Tarjeta();
	        nuevaTarjeta.setNumeroTarjeta(tarjeta.getNumero());
	        nuevaTarjeta.setFechaVencimiento(tarjeta.getFechaVencimiento());
	        nuevaTarjeta.setEstado("activa");
	        nuevaTarjeta.setIdCuenta(new Cuenta()); // Asume que ya tienes una entidad Cuenta.

	        return repo.save(nuevaTarjeta);
	    }

	}