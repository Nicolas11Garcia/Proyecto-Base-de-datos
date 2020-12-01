package com.nico.basededatos.dao;

import com.nico.basededatos.MyConnection.MyConnection;
import com.nico.basededatos.clases.*;
import com.nico.basededatos.opciones.DesactivarProduto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private MyConnection myLink;


    public Dao(MyConnection myLink) {
        this.myLink = myLink;
    }

    public void AgregarUser(Trabajador usuario) {
        String sql = "INSERT INTO trabajador VALUES (NULL,'" + usuario.getUsuario() + "',SHA2('" + usuario.getPass() + "',0))";
        Connection con = myLink.getCon();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Trabajador> verUsuarios() {
        String sql = "SELECT * FROM trabajador";
        List<Trabajador> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String nombres = resultSet.getString(2);
                String pass = resultSet.getString(3);

                Trabajador trabajador = new Trabajador(nombres, pass);
                lista.add(trabajador);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public List<TrabajadorConID> mostrarUsuariosID() {
        String sql = "SELECT * FROM trabajador";
        List<TrabajadorConID> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);

                TrabajadorConID trabajador = new TrabajadorConID(id, nombre);
                lista.add(trabajador);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public void IngresarProducto(Producto producto) {
        String sql = "CALL ingresar_producto('" + producto.getNombre() + "'," + producto.getPrecio() + ")";
        Connection con = myLink.getCon();

        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Producto> verProductos() {
        String sql = "SELECT * FROM producto";
        List<Producto> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String nombre = resultSet.getString(2);
                int precio = resultSet.getInt(3);

                Producto trabajador = new Producto(nombre, precio);
                lista.add(trabajador);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public String buscarProducto(IDproducto buscar) {
        String sql = "SELECT ver_producto(" + buscar.getId() + ")";
        Connection con = myLink.getCon();
        String producto = null;
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                producto = resultSet.getString(1);
            }
            return producto;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return producto;
    }

    public void DesactivarProducto(IDproducto id) {
        String sql = "CALL desactivar_producto(" + id.getId() + ")";
        Connection con = myLink.getCon();

        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<IDproducto> IDdeProductos() {
        String sql = "SELECT * FROM producto";
        List<IDproducto> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                IDproducto productoID = new IDproducto(id);
                lista.add(productoID);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public void ActivarProducto(IDproducto id) {
        String sql = "CALL activar_producto(" + id.getId() + ")";
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<FullProducto> VerProductosActivos() {
        String sql = "SELECT * FROM producto WHERE activo = 1";
        List<FullProducto> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                int precio = resultSet.getInt(3);

                FullProducto productoFull = new FullProducto(id, nombre, precio);
                lista.add(productoFull);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public void CambiarUser(CambiarUsuarioAtributo cambiarUser) {
        String sql = "CALL cambiar_user('" + cambiarUser.getUsuario() + "','" + cambiarUser.getContraseña() + "','" + cambiarUser.getNuevoUsuario() + "')";
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Inactivos> verInactivos() {
        String sql = "SELECT * FROM productos_inactivos";
        List<Inactivos> listado = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int id_producto = resultSet.getInt(2);
                String producto = resultSet.getString(3);
                int precio = resultSet.getInt(4);
                String desactivado = resultSet.getString(5);

                Inactivos inact = new Inactivos(id, id_producto, producto, precio, desactivado);
                listado.add(inact);
            }
            return listado;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listado;
    }

    public void CambiarContrasena(Contrasena nuevaContrasena) {
        String sql = "CALL cambiar_pass('" + nuevaContrasena.getUser() + "','" + nuevaContrasena.getPass() + "','" + nuevaContrasena.getNewPass() + "')";
        Connection con = myLink.getCon();

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Total> totalVendido(FechaDesdeHasta fecha2) {
        String sql = "CALL ver_pro_fecha_total('"+fecha2.getFechaDesde()+"','"+fecha2.getFechaHasta()+"')";
        List<Total> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String nombre = resultSet.getString(1);
                int cantidadP = resultSet.getInt(2);
                int precioU = resultSet.getInt(3);
                String fecha = resultSet.getString(4);
                int total = resultSet.getInt(5);

                Total total1 = new Total(nombre,cantidadP,precioU,fecha,total);
                lista.add(total1);
            }
            return lista;
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null,"El formato es invalido o no hay ventas en la fecha asignada");

        }
        return lista;
    }

    public List<proIDantiguos> listaIDantiguosDesactivados() {
        String sql = "SELECT * FROM productos_inactivos";

        List<proIDantiguos> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int id = resultSet.getInt(2);

                proIDantiguos total1 = new proIDantiguos(id);
                lista.add(total1);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

















}
