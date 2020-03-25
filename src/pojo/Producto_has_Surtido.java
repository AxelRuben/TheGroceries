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
    private int Producto_idProducto;
    private int Surtido_idSurtido;
    private int cantidad;

    public Producto_has_Surtido() {
    }

    public Producto_has_Surtido(int Producto_idProducto, int Surtido_idSurtido, int cantidad) {
        this.Producto_idProducto = Producto_idProducto;
        this.Surtido_idSurtido = Surtido_idSurtido;
        this.cantidad = cantidad;
    }
    
    public Producto_has_Surtido(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
    
    
}
