package imb.progra3.gc.grupo3.service;

import java.util.List;

import imb.progra3.gc.grupo3.entity.Transaccion;

public interface ITransaccionService {
	public List<Transaccion> findAll();

	public Transaccion findById(Long id);

	public boolean exists(Long id);

	public Transaccion save(Transaccion transaccion);

	public void delete(Long id);

	public List<Transaccion> findByTipo(String tipo);

	public Transaccion getById(Long id);

}
