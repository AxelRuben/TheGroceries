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
public class Surtido {
    private int idSurtido;
    private double total;
    private double pago;
    private double cambio;

    public Surtido() {
    }

    public Surtido(int idSurtido, double total, double pago, double cambio) {
        this.idSurtido = idSurtido;
        this.total = total;
        this.pago = pago;
        this.cambio = cambio;
    }
    
    public Surtido(double total, double pago, double cambio) {
        this.total = total;
        this.pago = pago;
        this.cambio = cambio;
    }

    public int getIdSurtido() {
        return idSurtido;
    }

    public void setIdSurtido(int idSurtido) {
        this.idSurtido = idSurtido;
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
    
    
}
