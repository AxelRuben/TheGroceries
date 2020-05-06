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
public class Ventas_has_Producto {
    private int idvhp;
    private int ventas_idventas;
    private int producto_idproducto;
    private double cantidad;
    private double subtotal;

    public Ventas_has_Producto() {
    }

    public Ventas_has_Producto(int idvhp, int ventas_idventas, int producto_idproducto, double cantidad, double subtotal) {
        this.idvhp = idvhp;
        this.ventas_idventas = ventas_idventas;
        this.producto_idproducto = producto_idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
     public Ventas_has_Producto( int ventas_idventas, int producto_idproducto, double cantidad, double subtotal) {
        this.ventas_idventas = ventas_idventas;
        this.producto_idproducto = producto_idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }


    public int getIdvhp() {
        return idvhp;
    }

    public void setIdvhp(int idvhp) {
        this.idvhp = idvhp;
    }

    public int getVentas_idventas() {
        return ventas_idventas;
    }

    public void setVentas_idventas(int ventas_idventas) {
        this.ventas_idventas = ventas_idventas;
    }

    public int getProducto_idproducto() {
        return producto_idproducto;
    }

    public void setProducto_idproducto(int producto_idproducto) {
        this.producto_idproducto = producto_idproducto;
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
