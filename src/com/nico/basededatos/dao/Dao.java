package com.nico.basededatos.dao;

import com.nico.basededatos.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private MyConnection myLink;


    public Dao(MyConnection myLink){
        this.myLink = myLink;
    }

    public void AgregarUser(Trabajador usuario){
        String sql = "INSERT INTO trabajador VALUES (NULL,'"+usuario.getUsuario()+"',SHA2('"+usuario.getPass()+"',0))";
        Connection con = myLink.getCon();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();        }
    }

    public List<Trabajador> verUsuarios(){
        String sql = "SELECT * FROM trabajador";
        List<Trabajador> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                String nombres = resultSet.getString(2);
                String pass = resultSet.getString(3);

                Trabajador trabajador = new Trabajador(nombres,pass);
                lista.add(trabajador);
            }
            return  lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public List<TrabajadorConID> mostrarUsuariosID(){
        String sql = "SELECT * FROM trabajador";
        List<TrabajadorConID> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);

                TrabajadorConID trabajador = new TrabajadorConID(id,nombre);
                lista.add(trabajador);
            }
            return  lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public void IngresarProducto(Producto producto){
        String sql = "CALL ingresar_producto('"+producto.getNombre()+"',"+producto.getPrecio()+")";
        Connection con = myLink.getCon();

        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Producto> verProductos(){
        String sql = "SELECT * FROM producto";
        List<Producto> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                String nombre = resultSet.getString(2);
                int precio  = resultSet.getInt(3);

                Producto trabajador = new Producto(nombre,precio);
                lista.add(trabajador);
            }
            return  lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public String buscarProducto(IDproducto buscar){
        String sql = "SELECT ver_producto("+buscar.getId()+")";
        Connection con = myLink.getCon();
        String producto = null;
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                producto = resultSet.getString(1);
            }
            return producto;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  producto;
    }

    public void DesactivarProducto(IDproducto id){
        String sql = "CALL desactivar_producto("+id.getId()+")";
        Connection con = myLink.getCon();

        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<IDproducto> IDdeProductos(){
        String sql = "SELECT * FROM producto";
        List<IDproducto> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id  = resultSet.getInt(1);
                IDproducto productoID = new IDproducto(id);
                lista.add(productoID);
            }
            return  lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public void ActivarProducto(IDproducto id){
        String sql = "CALL activar_producto("+id.getId()+")";
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<FullProducto> VerProductosActivos(){
        String sql = "SELECT * FROM producto WHERE activo = 1";
        List<FullProducto> lista = new ArrayList();
        Connection con = myLink.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                int precio  = resultSet.getInt(3);

                FullProducto productoFull = new FullProducto(id,nombre,precio);
                lista.add(productoFull);
            }
            return  lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }















}
