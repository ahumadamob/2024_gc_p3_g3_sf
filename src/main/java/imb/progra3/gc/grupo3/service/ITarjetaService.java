package imb.progra3.gc.grupo3.service;

import java.util.List;

import imb.progra3.gc.grupo3.dto.TarjetaDTO;
import imb.progra3.gc.grupo3.entity.Tarjeta;
import jakarta.validation.Valid;


public interface ITarjetaService {

	public List<Tarjeta> findAll();
	public Tarjeta findById(Long id);
	public boolean exists(Long id);
	public Tarjeta save(Tarjeta tarjeta);
	public void delete(Long id);
	public List<Tarjeta> findBynumeroTarjeta(String numeroTarjeta);
	boolean bloquearTarjeta(Long id);
	public Tarjeta crearTarjeta(@Valid TarjetaDTO tarjetaDTO);

}