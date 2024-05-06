package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class CajeroAutomatico {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Id_Cajero;
    private String ubicacion;
    private Date fecha_instalacion;
    private boolean estado; //1-Activo 0-Inactivo
}