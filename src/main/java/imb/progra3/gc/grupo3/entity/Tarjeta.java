package imb.progra3.gc.grupo3.entity;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
	public class Tarjeta extends BaseEntity {
		
		private String numeroTarjeta;
		
		private Date fechaVencimiento;
		
		private String estado;
		
		private Long IdCuenta;

		public String getNumeroTarjeta() {
			return numeroTarjeta;
		}

		public void setNumeroTarjeta(String numeroTarjeta) {
			this.numeroTarjeta = numeroTarjeta;
		}

		public Date getFechaVencimiento() {
			return fechaVencimiento;
		}

		public void setFechaVencimiento(Date fechaVencimiento) {
			this.fechaVencimiento = fechaVencimiento;
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
		
		
	}
	
