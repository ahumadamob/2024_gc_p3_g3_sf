package imb.progra3.gc.grupo3.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
	public class Tarjeta extends BaseEntity {
		
		private String numeroTarjeta;
		
		private LocalDate fechaVencimiento;
		
		private String estado;
		
		private Long IdCuenta;
		
		 @ManyToOne
		  @JoinColumn(name = "id_cuenta", nullable = false) // Clave foránea en la tabla 'tarjeta'
		  private Cuenta cuenta;

		public String getNumeroTarjeta() {
			return numeroTarjeta;
		}

		public void setNumeroTarjeta(String numeroTarjeta) {
			this.numeroTarjeta = numeroTarjeta;
		}

		public LocalDate getFechaVencimiento() {
			return fechaVencimiento;
		}

		public void setFechaVencimiento(LocalDate localDate) {
			this.fechaVencimiento = localDate;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public Long getIdCuenta() {
			return IdCuenta;
		}

		public void setIdCuenta(Long idCuenta) {
			IdCuenta = idCuenta;
		}

		public void setIdCuenta(Cuenta cuenta) {
		
		}

		public Cuenta getCuenta() {
			return cuenta;
		}

		public void setCuenta(Cuenta cuenta) {
			this.cuenta = cuenta;
		}
		
		
		
		
	}
	
