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
public class Producto {
    private int idProducto;
     private String nombre;
     private int tipo;
     private String codigo_barra;
     private int stock;
     private int proveedor_idProveedor;
     private double costo;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, int tipo, String codigo_barra, int stock, int proveedor_idProveedor, double costo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.codigo_barra = codigo_barra;
        this.stock = stock;
        this.proveedor_idProveedor = proveedor_idProveedor;
        this.costo = costo;
    }

   public Producto( String nombre, int tipo, String codigo_barra, int stock, int proveedor_idProveedor, double costo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.codigo_barra = codigo_barra;
        this.stock = stock;
        this.proveedor_idProveedor = proveedor_idProveedor;
        this.costo = costo;
    } 

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProveedor_idProveedor() {
        return proveedor_idProveedor;
    }

    public void setProveedor_idProveedor(int proveedor_idProveedor) {
        this.proveedor_idProveedor = proveedor_idProveedor;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
   
}
