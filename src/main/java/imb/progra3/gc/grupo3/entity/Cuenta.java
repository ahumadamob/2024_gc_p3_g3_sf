package imb.progra3.gc.grupo3.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Cuenta extends BaseEntity {


    private String tipoCuenta;
    private Double saldo;
    private LocalDate fechaApertura;
    private Long idCliente;
    private String numeroCuenta;

    // Getters y setters


    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double nuevoSaldo) {
        this.saldo = nuevoSaldo;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}

