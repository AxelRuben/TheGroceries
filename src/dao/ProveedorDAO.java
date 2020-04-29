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
import pojo.Proveedor;

/**
 *
 * @author lizbe
 */
public class ProveedorDAO {
      public int insertar(Proveedor pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insintoprov(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getTelefono());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar proveedor " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
       }
    public boolean actualizar_proveedor(Proveedor pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Proveedor proveedor = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update proveedor set nombre=?,telefono=?");
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getTelefono());
            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Proveedor " + e);

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
        String encabezados[] = {"Id", "Nombre","Teléfono"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from proveedor");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Proveedor pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdproveedor();
                ob[1] = pojo.getNombre().toUpperCase();
                ob[2] = pojo.getTelefono();
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla proveedor " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    
     public Proveedor selectedProveedor(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Proveedor pojo = new Proveedor();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from proveedor where idproveedor==0");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar proveedor " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
     
    public DefaultComboBoxModel cargarCombo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from proveedor");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione a su Proveedor");
            while (rs.next()) {
                Proveedor pojo = inflaPOJO(rs);
                dt.addElement(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el modelo Proveedor " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     
    private static Proveedor inflaPOJO(ResultSet rs) {

        Proveedor POJO= new Proveedor();
        try {
            POJO.setIdproveedor(rs.getInt("idproveedor"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setTelefono(rs.getString("telefono"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Proveedor: " + ex);
        }
        return POJO;
    }
    
}
