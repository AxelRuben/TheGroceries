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
                System.out.println("ID insertada " + id);
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
            st = con.prepareStatement("update proveedor set nombre=?,telefono=? where idproveedor=?");
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getTelefono());
            st.setInt(3, pojo.getIdproveedor());
            int x = st.executeUpdate();
            if (x != 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Proveedor " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return false;
    }

    public boolean setProveedorAct(boolean op, int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update proveedor set activo=? where idproveedor=?");
            st.setBoolean(1, op);
            st.setInt(2, id);

            int x = st.executeUpdate();
            if (x != 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al activar o desactivar el Proveedor " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return false;
    }

    public DefaultTableModel cargarModelo(int op) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre", "Teléfono", "Activo"};
        try {
            con = Conexion.getConnection();
            if (op == 0) {
                st = con.prepareStatement("select*from proveedor");
            } else if (op == 1) {
                st = con.prepareStatement("select*from proveedor where activo=1");
            } else if (op == 2) {
                st = con.prepareStatement("select*from proveedor where activo=0");
            }
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Proveedor pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdproveedor();
                ob[1] = pojo.getNombre();
                ob[2] = pojo.getTelefono();
                if (pojo.isActivo()) {
                    ob[3] = "Activo";
                } else {
                    ob[3] = "Inactivo";
                }
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
            st = con.prepareStatement("select*from proveedor where idproveedor=?");
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

    public DefaultComboBoxModel cargarCombo(int op) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            if (op == 0) {
                st = con.prepareStatement("select * from proveedor");
            } else if (op == 1) {
                st = con.prepareStatement("select * from proveedor where activo=1");
            }
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione a su proveedor");
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

        Proveedor POJO = new Proveedor();
        try {
            POJO.setIdproveedor(rs.getInt("idproveedor"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setTelefono(rs.getString("telefono"));
            POJO.setActivo(rs.getBoolean("activo"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Proveedor: " + ex);
        }
        return POJO;
    }

}
