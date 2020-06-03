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
public class Proveedor {
    private int idproveedor;
    private String nombre;
    private String telefono;
    private boolean activo;

    public Proveedor() {
    }

    public Proveedor(int idproveedor, String nombre, String telefono, boolean activo) {
        this.idproveedor = idproveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = activo;
    }
    
    public Proveedor(int idproveedor, String nombre, String telefono) {
        this.idproveedor = idproveedor;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public Proveedor(String nombre, String telefono, boolean activo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = activo;
    }
    
    public Proveedor(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return nombre;
    }
  
}
