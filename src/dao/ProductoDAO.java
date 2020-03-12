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
import pojo.Producto;


/**
 *
 * @author lizbe
 */


public class ProductoDAO {
    
    public int insertar(Producto pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("insert into producto(nom,tipo,cod_bar,stock,proveedor_idproveedor,costo) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setInt(2, pojo.getTipo());
            st.setString(3, pojo.getCodigo_barra());
            st.setInt(4, pojo.getStock());
            st.setInt(5, pojo.getProveedor_idProveedor());
            st.setDouble(6, pojo.getCosto());
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
    public boolean actualizar_producto(Producto pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Producto producto = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update producto set nombre=?,tipo=?,cod_bar=?, stock=?,proveedor_idproveedor=?, costo=?");
            st.setString(1, pojo.getNombre());
            st.setInt(2, pojo.getTipo());
            st.setString(3, pojo.getCodigo_barra());
            st.setInt(4, pojo.getStock());
            st.setInt(5, pojo.getProveedor_idProveedor());
            st.setDouble(6, pojo.getCosto());

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar producto " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
  
    public boolean delete_producto(int id) {
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
            System.out.println("Error al eliminar producto: " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }
    
    public DefaultTableModel cargarModeloA(int op) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre","Tipo", "Stock","Costo"};
        try {
            con = Conexion.getConnection();
            if (op==0) {
                st = con.prepareStatement("select*from producto");
            } else if(op==1){
                st = con.prepareStatement("select*from producto where stock!=0");
            } else if(op==2){
                st = con.prepareStatement("select*from producto where stock==0");
            }
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[4];
                Producto pojo = inflaPOJO(rs);
                ob[1] = pojo.getIdProducto();
                ob[2] = pojo.getNombre().toUpperCase();
                ob[3] = pojo.getTipo();
                ob[4] = pojo.getStock();
                ob[5] = pojo.getCosto();
                dt.addRow(ob);
            }          
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla producto " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return dt;
    }
     public Producto selectedProducto(int id) {
        Connection con = null;
        PreparedStatement st = null;
         Producto pojo = new Producto();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select*from producto where idprocucto==0");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pojo = inflaPOJO(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar producto " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return pojo;
    }
    private static Producto inflaPOJO(ResultSet rs) {

        Producto POJO= new Producto();
        try {
            POJO.setIdProducto(rs.getInt("idproducto"));
            POJO.setNombre(rs.getString("nom"));
            POJO.setTipo(rs.getInt("tipo"));
            POJO.setCodigo_barra(rs.getString("cod_bar"));
            POJO.setStock(rs.getInt("sotck"));
            POJO.setProveedor_idProveedor(rs.getInt("proveedor_idproveedor"));
            POJO.setCosto(rs.getDouble("costo"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo producto: " + ex);
        }
        return POJO;
    }
}

