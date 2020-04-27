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
import pojo.Ventas;

/**
 *
 * @author lizbe
 */
public class VentasDAO {
     public int insertar(Ventas pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call procedure insintovent(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, pojo.getTotal());
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
    public boolean actualizar_Ventas(Ventas pojo) {
        Connection con = null;
        PreparedStatement st = null;
       Ventas ventas = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update ventas set idventas=?,total=?");
            st.setInt(1, pojo.getIdventas());
            st.setDouble(2, pojo.getTotal());
            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar ventas " + e);

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
        String encabezados[] = {"Id", "Total"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from ventas");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Ventas pojo = inflaPOJO(rs);
                ob[1] = pojo.getIdventas();
                ob[2] = pojo.getTotal();
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla ventas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Ventas selectedVentas(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Ventas pojo = new Ventas();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from ventas where idventas==0");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar ventas " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Ventas inflaPOJO(ResultSet rs) {

       Ventas POJO= new Ventas();
        try {
            POJO.setIdventas(rs.getInt("idVentas"));
            POJO.setTotal(rs.getDouble("Total"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo VentasS: " + ex);
        }
        return POJO;
    }
}
