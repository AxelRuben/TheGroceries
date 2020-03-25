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

    public Surtido() {
    }

    public Surtido(int idSurtido, double total) {
        this.idSurtido = idSurtido;
        this.total = total;
    }
    
    public Surtido(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdSurtido() {
        return idSurtido;
    }

    public void setIdSurtido(int idSurtido) {
        this.idSurtido = idSurtido;
    }
    
    
}
