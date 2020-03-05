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
import pojo.Usuario;

/**
 *
 * @author lizbe
 */
public class UsuarioDAO {
     public int insertar(Usuario pojo) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("call insert_cliente(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getContrasena());
            
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
    
    public Usuario selectedUsuario(String nom) {
        Connection con = null;
        PreparedStatement st = null;
         Usuario pojo = new Usuario();
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from usuarios where nombre=?");
            st.setString(1, nom);
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
    
    private static Usuario inflaPOJO(ResultSet rs) {

        Usuario POJO = new Usuario();
        try {
            POJO.setIdUsuario(rs.getInt("idusuarios"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setContrasena(rs.getString("contrasenia"));
            //POJO.setImage(rs.getString("phone"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo Dueño: " + ex);
        }
        return POJO;
    }
}
