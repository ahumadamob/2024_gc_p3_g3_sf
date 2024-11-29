package imb.progra3.gc.grupo3.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class UbicacionDTO {
	private String pais;
    private String ciudad;
    private String direccion;
    private String descripcion;	
	
	@Min(value = 1000, message = "El código postal debe ser como mínimo 1000.")
    @Max(value = 9430, message = "El código postal debe ser como máximo 9430.")
    private int codigoPostal;

	@Min(-90)
    @Max(90)
    private Double latitud;

    @Min(-180)
    @Max(180)
    private Double longitud;
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Integer getCodigoPostal() {
	    return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
	    this.codigoPostal = codigoPostal;
	}

	public Double getLatitud() {
        return latitud;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
