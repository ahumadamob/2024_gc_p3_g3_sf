package imb.progra3.gc.grupo3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ubicacion {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pais;
	private String ciudad;
	private String direccion;
	
	public Ubicacion() {}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
