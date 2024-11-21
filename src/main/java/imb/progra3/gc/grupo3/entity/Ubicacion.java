package imb.progra3.gc.grupo3.entity;

import jakarta.persistence.Entity;

@Entity
public class Ubicacion extends BaseEntity {
	
	private String pais;
	private String ciudad;
	private String direccion;
	private String descripcion; 
	
	public Ubicacion() {}
	
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

/*	public Object getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNombre(Object nombre) {
		// TODO Auto-generated method stub
		
	}*/
	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
