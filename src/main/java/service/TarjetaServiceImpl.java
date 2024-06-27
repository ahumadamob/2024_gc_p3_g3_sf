package service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.Tarjeta;
import tarjetarepository.TarjetaRepository;

@Service
public class TarjetaServiceImpl implements ITarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public List<Tarjeta> getAll() {
        return tarjetaRepository.findAll();
    }

    @Override
    public Tarjeta getById(Long id) {
        return tarjetaRepository.findById(id).orElse(null);
    }

    @Override
    public Tarjeta save(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public void delete(Long id) {
        tarjetaRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return tarjetaRepository.existsById(id);
    }
}