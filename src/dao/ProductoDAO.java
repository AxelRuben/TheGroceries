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

/**
 *
 * @author Bienvenido
 */
public class ProductoDAO {
    String sex="";
    public int insertar(Cliente pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL insert_cliente(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setString(2, sexoDM(pojo));
            st.setDate(3, pojo.getCumpleani());
            st.setString(4, pojo.getContacto());
            st.setString(5, pojo.getCorreo());
            st.setString(6, pojo.getDireccion());
            
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada "+id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar Dueño " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }
    public boolean actualizar_cliente(Cliente POJO) {
        
        Connection con = null;
        PreparedStatement st = null;
        Cliente cliente = POJO;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL update_cliente(?,?,?,?,?,?,?,?)");
            st.setInt(1, cliente.getIdcliente());
            st.setString(2, cliente.getNombre());
            st.setString(3, sexoDM(POJO));
            st.setDate(4, cliente.getCumpleani());
            st.setString(5, cliente.getContacto());
            st.setString(6, cliente.getCorreo());
            st.setString(7, cliente.getDireccion());
            st.setBoolean(8, cliente.isActivo());

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Cliente " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    public DefaultComboBoxModel cargarCombo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultComboBoxModel dt = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from cliente");
            dt = new DefaultComboBoxModel();
            ResultSet rs = st.executeQuery();
            dt.addElement("Seleccione a su Cliente");
            while (rs.next()) {
                Cliente pojo = inflaPOJO(rs);
                dt.addElement(pojo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el modelo cliente " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    
    public String sexoDM(Cliente pojo) {
        String sexA = pojo.getSexo();
        String sex = "";
        if (sexA.equalsIgnoreCase("Femenino")) {
            sex = "F";
        } else if (sexA.equalsIgnoreCase("Masculino")) {
            sex = "M";
        }
        return sex;
    }
    
    public boolean delete_cliente(int id) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL desactive_cliente(?)");
            st.setInt(1, id);
            int num = st.executeUpdate();
            if (num == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Cliente: " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public DefaultTableModel cargarModeloA(boolean activo) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre", "Contacto", "Estado"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from cliente where activo=?");
            st.setBoolean(1, activo);
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Cliente pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdcliente();
                ob[1] = pojo.getNombre().toUpperCase();
                ob[2] = pojo.getContacto();
            if (pojo.isActivo()) {
                ob[3] = "Activo";
                }else{
                ob[3] = "Inactivo";
                }

                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Cliente " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
    public DefaultTableModel cargarModelo() {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre", "Contacto", "Estado"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from cliente");
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Cliente pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdcliente();
                ob[1] = pojo.getNombre().toUpperCase();
                ob[2] = pojo.getContacto();
                if (pojo.isActivo()) {
                ob[3] = "Activo";
                }else{
                ob[3] = "Inactivo";
                }

                dt.addRow(ob);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla Dueño " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Cliente selectedCliente(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Cliente pojo = new Cliente();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("CALL select_a_cliente(?)");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar Dueno " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Cliente inflaPOJO(ResultSet rs) {

        Cliente POJO = new Cliente();
        try {
            POJO.setIdcliente(rs.getInt("idcliente"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setSexo(rs.getString("sexo"));
            POJO.setCumpleani(rs.getDate("cumplea"));
            POJO.setContacto(rs.getString("contacto"));
            POJO.setCorreo(rs.getString("correo"));
            POJO.setDireccion(rs.getString("direccion"));
            POJO.setActivo(rs.getBoolean("activo"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Dueño: " + ex);
        }
        return POJO;
    }
}
