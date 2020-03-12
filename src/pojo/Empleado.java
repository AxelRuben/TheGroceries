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
public class Empleado {
    private int idempleado;
    private String nombre;
    private int telefono;
    private int direccion;
    private String estudios;

    public Empleado() {
    }

    public Empleado(int idempleado, String nombre, int telefono, int direccion, String estudios) {
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estudios = estudios;
    }
public Empleado( String nombre, int telefono, int direccion, String estudios) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estudios = estudios;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

  
}
