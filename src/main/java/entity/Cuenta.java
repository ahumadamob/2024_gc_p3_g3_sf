package entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne; comentado para que pueda compilar

@Entity
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipoCuenta;
	
	private double saldo;
	
	private Date fechaApertura;
	
	// lo siguiente está comentado para que pueda compilar
	//@ManyToOne comentado para que pueda compilar
	//private Cliente id_cliente;*/

}

