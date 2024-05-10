package entity;

import java.util.Date;

public class Tarjeta {
    // Atrib
    private int ID; // pk
    private String numeroTarjeta;
    private Date fechaVencimiento;
    private String estado; // Act, Bloq, Per
    private int ID_Cuenta; // fk Cuenta

    // Const
    public Tarjeta(int ID, String numeroTarjeta, Date fechaVencimiento, String estado, int ID_Cuenta) {
        this.ID = ID;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.ID_Cuenta = ID_Cuenta;
    }

    // Get/set
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    public int getID_Cuenta() {
        return ID_Cuenta;
    }

    public void setID_Cuenta(int ID_Cuenta) {
        this.ID_Cuenta = ID_Cuenta;
    }
}