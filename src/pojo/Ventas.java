/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author lizbe
 */
public class Ventas {
    private int idventas;
    private double total;
    private double pago;
    private double cambio;
    private int empleado_idempleado;

    public Ventas() {
    }

    public Ventas(int idventas, double total, double pago, double cambio, int empleado_idempleado) {
        this.idventas = idventas;
        this.total = total;
        this.pago = pago;
        this.cambio = cambio;
        this.empleado_idempleado = empleado_idempleado;
    }

    public Ventas(double total, double pago, double cambio, int empleado_idempleado) {
        this.total = total;
        this.pago = pago;
        this.cambio = cambio;
        this.empleado_idempleado = empleado_idempleado;
    }

    public int getIdventas() {
        return idventas;
    }

    public void setIdventas(int idventas) {
        this.idventas = idventas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public int getEmpleado_idempleado() {
        return empleado_idempleado;
    }

    public void setEmpleado_idempleado(int empleado_idempleado) {
        this.empleado_idempleado = empleado_idempleado;
    }
    
    
    
}
