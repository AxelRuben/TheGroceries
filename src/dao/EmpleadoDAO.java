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
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import pojo.Empleado;

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
            st = con.prepareStatement("call insintoempl(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getTelefono());
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
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update empleados set nombreC=?,telefono=?,direccion=?, estudios=? where idempleados=?");
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getTelefono());
            st.setString(3, pojo.getDireccion());
            st.setString(4, pojo.getEstudios());
            st.setInt(5, pojo.getIdempleado());

            int x = st.executeUpdate();
            if (x != 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Empleado " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return false;
    }
    
    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre","Teléfono"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from empleados");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Empleado pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdempleado();
                ob[1] = pojo.getNombre().toUpperCase();
                ob[2] = pojo.getTelefono();
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
    
    public DefaultComboBoxModel cargarCombo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from empleados");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione a su Empleado");
            while (rs.next()) {
                Empleado pojo = inflaPOJO(rs);
                dt.addElement(pojo.toString().toUpperCase());
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el modelo Empleado " + e);
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
            st = con.prepareStatement("select * from empleados where idempleados=?");
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
            POJO.setTelefono(rs.getString("telefono"));
            POJO.setDireccion(rs.getString("direccion"));
            POJO.setEstudios(rs.getString("estudios"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Empleado: " + ex);
        }
        return POJO;
    }
}
