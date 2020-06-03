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
    private String telefono;
    private String direccion;
    private String estudios;
    private boolean activo;

    public Empleado() {
    }

    public Empleado(int idempleado, String nombre, String telefono, String direccion, String estudios, boolean activo) {
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estudios = estudios;
        this.activo = activo;
    }

    public Empleado(int idempleado, String nombre, String telefono, String direccion, String estudios) {
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estudios = estudios;
    }

    public Empleado(String nombre, String telefono, String direccion, String estudios, boolean activo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estudios = estudios;
        this.activo = activo;
    }

    public Empleado(String nombre, String telefono, String direccion, String estudios) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
