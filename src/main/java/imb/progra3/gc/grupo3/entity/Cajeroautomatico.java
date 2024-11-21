package imb.progra3.gc.grupo3.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;


@Entity
public class Cajeroautomatico extends BaseEntity {
    private String ubicacion;

    private LocalDate fechaInstalacion;

    private String estado;

    // Getters y Setters

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(LocalDate fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}