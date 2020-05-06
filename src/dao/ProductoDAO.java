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

    public int insertar(Producto pojo, boolean ccd) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        int id = 0;
        try {
            con = Conexion.getConnection();
            if (ccd) {
                st = con.prepareStatement("call insintoprod(?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                st.setString(1, pojo.getNombre());
                st.setString(2, pojo.getTipo());
                st.setString(3, pojo.getCodigo_barra());
                st.setDouble(4, pojo.getStock());
                st.setInt(5, pojo.getProveedor_idProveedor());
                st.setDouble(6, pojo.getCostoalcl());
                st.setDouble(7, pojo.getCostoaldu());
            } else {
                st = con.prepareStatement("call insintoprodsc(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                st.setString(1, pojo.getNombre());
                st.setString(2, pojo.getTipo());
                st.setDouble(3, pojo.getStock());
                st.setInt(4, pojo.getProveedor_idProveedor());
                st.setDouble(5, pojo.getCostoalcl());
                st.setDouble(6, pojo.getCostoaldu());
            }
            id = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("ID insertada " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar producto " + e);

        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return id;
    }

    public boolean actualizar_stock(int id, double cant) {

        Connection con = null;
        PreparedStatement st = null;
        int idd = 0;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update producto set stock=? where idproducto=?");
            st.setDouble(1, cant);
            st.setInt(2, id);
            idd = st.executeUpdate();

            int x = st.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar empleado " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(st);
        }
        return true;
    }

    public boolean actualizar_producto(Producto pojo) {
        Connection con = null;
        PreparedStatement st = null;
        Producto producto = pojo;
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("update producto set nombre=?,tipo=?,cod_bar=?, stock=?,proveedor_idproveedor=?, costoalcl=?, costoaldu=? where idproducto=?");
            st.setString(1, pojo.getNombre());
            st.setString(2, pojo.getTipo());
            st.setString(3, pojo.getCodigo_barra());
            st.setDouble(4, pojo.getStock());
            st.setInt(5, pojo.getProveedor_idProveedor());
            st.setDouble(6, pojo.getCostoalcl());
            st.setDouble(7, pojo.getCostoaldu());
            st.setInt(8, pojo.getIdProducto());

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

    public DefaultTableModel cargarModeloA(int op, int op2) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = new String[6];
        String encabezados2[] = new String[5];
        if (op2 == 0) {
            encabezados[0] = "Id";
            encabezados[1] = "Nombre";
            encabezados[2] = "Tipo";
            encabezados[3] = "Stock";
            encabezados[4] = "Costo Al Cliente";
            encabezados[5] = "Costo al Dueño";
        } else if (op2 == 1 || op2 == 2) {
            encabezados2[0] = "Id";
            encabezados2[1] = "Nombre";
            encabezados2[2] = "Tipo";
            encabezados2[3] = "Stock";
            encabezados2[4] = "Costo";
        }
        try {
            con = Conexion.getConnection();
            if (op == 0) {
                st = con.prepareStatement("select*from producto");
            } else if (op == 1) {
                st = con.prepareStatement("select*from producto where stock!=0");
            } else if (op == 2) {
                st = con.prepareStatement("select*from producto where stock=0");
            }
            dt = new DefaultTableModel();
            if (op2 == 0) {
                dt.setColumnIdentifiers(encabezados);
            } else if (op2 == 1 || op2 == 2) {
                dt.setColumnIdentifiers(encabezados2);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (op2 == 0) {
                    Object ob[] = new Object[6];
                    Producto pojo = inflaPOJO(rs);
                    ob[0] = pojo.getIdProducto();
                    ob[1] = pojo.getNombre().toUpperCase();
                    ob[2] = pojo.getTipo();
                    ob[3] = pojo.getStock();
                    ob[4] = pojo.getCostoalcl();
                    ob[5] = pojo.getCostoaldu();
                    dt.addRow(ob);
                } else if (op2 == 1 || op2 == 2) {
                    Object ob[] = new Object[5];
                    Producto pojo = inflaPOJO(rs);
                    ob[0] = pojo.getIdProducto();
                    ob[1] = pojo.getNombre().toUpperCase();
                    ob[2] = pojo.getTipo();
                    ob[3] = pojo.getStock();
                    if (op2 == 1) {
                        ob[4] = pojo.getCostoalcl();
                    } else if (op2 == 2) {
                        ob[4] = pojo.getCostoaldu();
                    }
                    dt.addRow(ob);
                }
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

    public DefaultTableModel cargarModeloP(int idproveedor, int op) {
        Connection con = null;
        PreparedStatement st = null;
        DefaultTableModel dt = null;
        String encabezados[] = {"Id", "Nombre", "Tipo", "Stock", "Costo"};
        try {
            con = Conexion.getConnection();
            st = con.prepareStatement("select * from producto where proveedor_idproveedor=? and stock!=0");
            st.setInt(1, idproveedor);
            dt = new DefaultTableModel();
            dt.setColumnIdentifiers(encabezados);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[5];
                Producto pojo = inflaPOJO(rs);
                ob[0] = pojo.getIdProducto();
                ob[1] = pojo.getNombre().toUpperCase();
                ob[2] = pojo.getTipo();
                ob[3] = pojo.getStock();
                if (op == 0) {
                    ob[4] = pojo.getCostoalcl();
                } else if (op == 1) {
                    ob[4] = pojo.getCostoaldu();
                } else {
                    System.out.println("Valor no valido");
                }
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
            st = con.prepareStatement("select * from producto where idproducto=?");
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

        Producto POJO = new Producto();
        try {
            POJO.setIdProducto(rs.getInt("idproducto"));
            POJO.setNombre(rs.getString("nombre"));
            POJO.setTipo(rs.getString("tipo"));
            POJO.setCodigo_barra(rs.getString("cod_bar"));
            POJO.setStock(rs.getDouble("stock"));
            POJO.setProveedor_idProveedor(rs.getInt("proveedor_idproveedor"));
            POJO.setCostoalcl(rs.getDouble("costoalcl"));
            POJO.setCostoaldu(rs.getDouble("costoaldu"));
        } catch (SQLException ex) {
            System.out.println("Error al inflar pojo producto: " + ex);
        }
        return POJO;
    }
}
