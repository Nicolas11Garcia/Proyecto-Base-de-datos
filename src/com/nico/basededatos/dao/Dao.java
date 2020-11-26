package com.nico.basededatos.dao;

import com.nico.basededatos.MyConnection;
import com.nico.basededatos.Trabajador;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import javax.swing.plaf.nimbus.State;
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
            JOptionPane.showMessageDialog(null,"El usuario que desea ingresar ya existe, porfavor ingrese otro nuevamente");
        }
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











}
