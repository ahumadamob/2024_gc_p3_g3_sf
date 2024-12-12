package imb.progra3.gc.grupo3.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Cuenta extends BaseEntity {

    @Column(nullable = false)
    private String tipoCuenta;

    @Column(nullable = false)
    private Double saldo;

    @Column(nullable = false)
    private LocalDate fechaApertura;

    @Column(nullable = false, unique = true)
    private String numeroCuenta;

    @Column(nullable = false)
    private String titular;

    // Relación con Cliente (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

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

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getIdCliente() {
        return cliente != null ? cliente.getId() : null;
    }

    // ToString, Equals, HashCode (opcional, pero útil en algunas situaciones)
    @Override
    public String toString() {
        return "Cuenta{" +
                "tipoCuenta='" + tipoCuenta + '\'' +
                ", saldo=" + saldo +
                ", fechaApertura=" + fechaApertura +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", titular='" + titular + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}


