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
import pojo.Producto_has_Surtido;

/**
 *
 * @author lizbe
 */
public class Producto_has_surtidoDAO {
      public int insertar(Producto_has_Surtido pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insintoprodhsurt(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, pojo.getProducto_idProducto());
            st.setInt(2, pojo.getSurtido_idSurtido());
            st.setDouble(3, pojo.getCantidad());
            st.setDouble(4, pojo.getSubtotal());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar producto_has_surtido " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
       }
    public boolean actualizar_Producto_has_Surtido(Producto_has_Surtido pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Producto_has_Surtido producto_has_Surtido = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update Producto_has_Surtido set producto_idproducto=?,surtido_idsurtido=?,cantidad=?,subtotal=?");
            st.setInt(1, pojo.getProducto_idProducto());
            st.setInt(2, pojo.getSurtido_idSurtido());
            st.setDouble(3, pojo.getCantidad());
            st.setDouble(4, pojo.getSubtotal());
            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Producto_has_Surtido " + e);

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
        String encabezados[] = {"Id", "Surtido","Cantidad"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from Producto_has_Surtido");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Producto_has_Surtido pojo = inflaPOJO(rs);
                ob[1] = pojo.getProducto_idProducto();
                ob[2] = pojo.getSurtido_idSurtido();
                ob[3] = pojo.getCantidad();
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Producto_has_Surtido" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    public DefaultTableModel cargarModeloVPDS(int id) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Surtido","Cantidad"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select distinct p.nombre, p.costoalcl, phs.cantidad, phs.subtotal from surtido s,producto_has_surtido phs, producto p where phs.producto_idproducto=p.idproducto and phs.surtido_idsurtido=?");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                ob[0] = rs.getString(1);
                ob[1] = rs.getDouble(2);
                ob[2] = rs.getDouble(3);
                ob[3] = rs.getDouble(4);
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Producto_has_Surtido" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Producto_has_Surtido selectedProducto_has_Surtido(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Producto_has_Surtido pojo = new Producto_has_Surtido();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from Producto_has_Surtido where idproducto==0");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Producto_has_Surtido " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Producto_has_Surtido inflaPOJO(ResultSet rs) {

        Producto_has_Surtido POJO= new Producto_has_Surtido();
        try {
            POJO.setProducto_idProducto(rs.getInt("Producto_idproducto"));
            POJO.setSurtido_idSurtido(rs.getInt("surtido_idsurtido"));
            POJO.setCantidad(rs.getInt("cantidad"));
            POJO.setSubtotal(rs.getDouble("subtotal"));
 
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Producto_has_Surtido: " + ex);
        }
        return POJO;
    }
}
