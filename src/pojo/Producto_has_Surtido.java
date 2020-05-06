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
public class Producto_has_Surtido {
    private int idphs;
    private int Producto_idProducto;
    private int Surtido_idSurtido;
    private double cantidad;
    private double subtotal;
   
    public Producto_has_Surtido() {
    
} 

    public Producto_has_Surtido(int idphs, int Producto_idProducto, int Surtido_idSurtido, double cantidad, double subtotal) {
        this.idphs = idphs;
        this.Producto_idProducto = Producto_idProducto;
        this.Surtido_idSurtido = Surtido_idSurtido;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
     public Producto_has_Surtido( int Producto_idProducto, int Surtido_idSurtido, double cantidad, double subtotal) {
        this.Producto_idProducto = Producto_idProducto;
        this.Surtido_idSurtido = Surtido_idSurtido;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getIdphs() {
        return idphs;
    }

    public void setIdphs(int idphs) {
        this.idphs = idphs;
    }

    public int getProducto_idProducto() {
        return Producto_idProducto;
    }

    public void setProducto_idProducto(int Producto_idProducto) {
        this.Producto_idProducto = Producto_idProducto;
    }

    public int getSurtido_idSurtido() {
        return Surtido_idSurtido;
    }

    public void setSurtido_idSurtido(int Surtido_idSurtido) {
        this.Surtido_idSurtido = Surtido_idSurtido;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
     
   
    
}
    

    