/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import pojo.Empleado;
import pojo.Producto;

/**
 *
 * @author lizbe
 */
public class EmpleadoDAO {
    public int insertar(Empleado pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("insert into empleados(nombreC,telefono,direccion,esturdios) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setInt(2, pojo.getTelefono());
            st.setString(3, pojo.getDireccion());
            st.setString(4, pojo.getEstudios());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar producto " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
       }
    public boolean actualizar_producto(Empleado pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Empleado empleado = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update empleados set nombreC=?,telefono=?,direccion=?, estudios=? where idempleado=?");
            st.setString(1, pojo.getNombre());
            st.setInt(2, pojo.getTelefono());
            st.setString(3, pojo.getDireccion());
            st.setString(4, pojo.getEstudios());
            st.setInt(5, pojo.getIdempleado());

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Empleado " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre","Telefono"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from empleados");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Empleado pojo = inflaPOJO(rs);
                ob[1] = pojo.getIdempleado();
                ob[2] = pojo.getNombre().toUpperCase();
                ob[3] = pojo.getTelefono();
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla empleado " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Empleado selectedEmpleado(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Empleado pojo = new Empleado();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from empleados where idempleado==0");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar empleado " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Empleado inflaPOJO(ResultSet rs) {

        Empleado POJO= new Empleado();
        try {
            POJO.setIdempleado(rs.getInt("idempleados"));
            POJO.setNombre(rs.getString("nombreC"));
            POJO.setTelefono(rs.getInt("telefono"));
            POJO.setDireccion(rs.getString("direccion"));
            POJO.setEstudios(rs.getString("estudios"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Empleado: " + ex);
        }
        return POJO;
    }
}
