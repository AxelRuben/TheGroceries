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
import pojo.Ventas_has_Producto;

/**
 *
 * @author lizbe
 */
public class Ventas_has_productoDAO {
     public int insertar( Ventas_has_Producto  pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call procedure insintoventhprod(?,?,?,?", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, pojo.getVentas_idventas());
            st.setInt(2, pojo.getProducto_idproducto());
            st.setDouble(3, pojo.getCantidad());
            st.setDouble(4, pojo.getSubtotal());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar Ventas_has_Productos " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
       }
    public boolean actualizar_Ventas_has_Producto (Ventas_has_Producto pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Ventas_has_Producto ventas_has_Producto  = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update Ventas_has_Producto set Ventas_idventas=?,producto_idproducto=?,cantidad=?,subtotal=?");
            st.setInt(1, pojo.getVentas_idventas());
            st.setInt(2, pojo.getProducto_idproducto());
            st.setDouble(3, pojo.getCantidad());
            st.setDouble(4, pojo.getSubtotal());
            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Ventas_has_Producto  " + e);

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
        String encabezados[] = {"Id", "Subtotal","Cantidad"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from Ventas_has_Producto");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Ventas_has_Producto pojo = inflaPOJO(rs);
                ob[1] = pojo.getVentas_idventas();
                ob[2] = pojo.getProducto_idproducto();
                ob[3] = pojo.getCantidad();
                ob[4] = pojo.getCantidad();
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Ventas_has_Producto" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Ventas_has_Producto selectedVentas_has_Producto(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Ventas_has_Producto pojo = new Ventas_has_Producto();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from Ventas_has_Producto where idventas==0");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Ventas_has_Producto " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Ventas_has_Producto inflaPOJO(ResultSet rs) {
        Ventas_has_Producto POJO= new Ventas_has_Producto();
        try {
            POJO.setVentas_idventas(rs.getInt("Ventas_idventas"));
            POJO.setProducto_idproducto(rs.getInt("Producto_idproducto"));
            POJO.setCantidad(rs.getDouble("cantidad"));
            POJO.setSubtotal(rs.getDouble("subtotal"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar  Ventas_has_Producto: " + ex);
        }
        return POJO;
    }
}
