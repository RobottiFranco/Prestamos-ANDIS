package com.andis.prestamos.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long montoPrestamo;
    private double interes;
    private int cuotas;
    private Date fechainicio;
    private Long balance;
    private Long idCliente;
    private boolean pagado;

    public Prestamo() {
    }

    public Prestamo(Long id, Long montoPrestamo, double interes, int cuotas, Date fechainicio, Long balance,
            Long idCliente, boolean pagado) {
        this.id = id;
        this.montoPrestamo = montoPrestamo;
        this.interes = interes;
        this.cuotas = cuotas;
        this.fechainicio = fechainicio;
        this.balance = balance;
        this.idCliente = idCliente;
        this.pagado = pagado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getmontoPrestamo() {
        return montoPrestamo;
    }

    public void setmontoPrestamo(Long montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public boolean getPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    boolean isPagado() {
        return pagado;
    }

    @Override
    public String toString() {
        return "Prestamo [balance=" + balance + ", cuotas=" + cuotas + ", fechainicio=" + fechainicio + ", id=" + id
                + ", idCliente=" + idCliente + ", interes=" + interes + ", montoPrestamo=" + montoPrestamo + "]";
    }
}
