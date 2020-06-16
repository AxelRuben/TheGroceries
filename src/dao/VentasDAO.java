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
            st = con.prepareStatement("insert into ventas(total, pago, cambio, Empleados_idEmpleados) values(?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, pojo.getTotal());
            st.setDouble(2, pojo.getPago());
            st.setDouble(3, pojo.getCambio());
            st.setInt(4, pojo.getEmpleado_idempleado());
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar ventas " + e);

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
            st = con.prepareStatement("update ventas set total=?, pago=?, cambio=?, Empleado_idEmpleado=? where idVentas=?");
            st.setDouble(1, pojo.getTotal());
            st.setDouble(2, pojo.getPago());
            st.setDouble(3, pojo.getCambio());
            st.setInt(4, pojo.getEmpleado_idempleado());
            st.setInt(5, pojo.getIdventas());
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
                Object ob[] = new Object[2];
                Ventas pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdventas();
                ob[1] = pojo.getTotal();
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

    public DefaultTableModel cargarModeloVV() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Fecha", "Empleado"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select v.idVentas,v.fecha,e.nombreC from ventas v, empleados e where v.empleados_idEmpleados=e.idEmpleados");
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
            st = con.prepareStatement("select*from ventas where idventas=?");
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

    public DefaultTableModel cargarModeloFech(String fec) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Fecha", "Total"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select v.idVentas,v.fecha,e.nombreC from ventas v, empleados e where v.empleados_idEmpleados=e.idEmpleados and v.fecha BETWEEN '" + fec + " 00:00:00' AND '" + fec + " 23:59:59'");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[3];
                ob[0] = rs.getInt(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
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

    private static Ventas inflaPOJO(ResultSet rs) {

        Ventas POJO = new Ventas();
        try {
            POJO.setIdventas(rs.getInt("idVentas"));
            POJO.setTotal(rs.getDouble("Total"));
            POJO.setPago(rs.getDouble("Pago"));
            POJO.setCambio(rs.getDouble("Cambio"));
            POJO.setEmpleado_idempleado(rs.getInt("Empleados_idEmpleados"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo VentasS: " + ex);
        }
        return POJO;
    }
}
