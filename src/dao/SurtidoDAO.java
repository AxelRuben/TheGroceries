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
import pojo.Surtido;

/**
 *
 * @author lizbe
 */
public class SurtidoDAO {
     public int insertar(Surtido pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("insert into Surtido(total,pago,cambio) values(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, pojo.getTotal());
            st.setDouble(2, pojo.getPago());
            st.setDouble(3, pojo.getCambio());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar surtido " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
       }
    public boolean actualizar_Surtido(Surtido pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Surtido surtido = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update surtido set total =? where idsurtido=?");
            st.setDouble(1, pojo.getTotal());
            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al surtido " + e);

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
            st = con.prepareStatement("select*from surtido");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Surtido pojo = inflaPOJO(rs);
                ob[1] = pojo.getIdSurtido();
                ob[2] = pojo.getTotal();
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla surtido " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    
    public DefaultTableModel cargarModeloVS() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Total","Proveedor"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select distinct s.idsurtido,s.fecha,pr.nombre from surtido s, producto_has_surtido phs, producto p, proveedor pr where pr.idProveedor=p.proveedor_idProveedor and p.idproducto=phs.producto_idproducto and phs.surtido_idSurtido=s.idsurtido");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                ob[0] = rs.getInt(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3).toUpperCase();
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla surtido " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    public DefaultTableModel cargarModeloVPS(int id) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre","Costo","Cantidad"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select distinct p.nombre, p.costoalcl, phs.cantidad, phs.subtotal from surtido s,producto_has_surtido phs, producto p where phs.producto_idproducto=p.idproducto and phs.surtido_idsurtido=?");
            st.setInt(1, id);
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                ob[0] = rs.getString(1).toUpperCase();
                ob[1] = rs.getDouble(2);
                ob[2] = rs.getDouble(3);
                ob[3] = rs.getDouble(4);
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla surtido " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Surtido selectedSurtido(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Surtido pojo = new Surtido();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from surtido where idsurtido=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar surtido" + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Surtido inflaPOJO(ResultSet rs) {

        Surtido POJO= new Surtido ();
        try {
            POJO.setIdSurtido(rs.getInt("idSurtido"));
            POJO.setTotal(rs.getDouble("Total"));
            POJO.setPago(rs.getDouble("Pago"));
            POJO.setCambio(rs.getDouble("Cambio"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Surtido: " + ex);
        }
        return POJO;
    }
}
