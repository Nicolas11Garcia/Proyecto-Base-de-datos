package com.nico.basededatos;

import com.nico.basededatos.MyConnection.MyConnection;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class VerUser extends JFrame {
    private JTable table1;
    private JButton atrasButton;
    private JPanel verUser;
    private DefaultTableModel modeloTabla;

    public VerUser() throws SQLException {
        super("Menu");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(verUser);


        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "";
        MyConnection link = new MyConnection(ip,user,pass,db);

        Dao dao = new Dao(link);
        //Crear tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Usuario");

        table1.setModel(modeloTabla);
        List<TrabajadorConID> lista1= dao.mostrarUsuariosID();
        for(TrabajadorConID p : lista1){
            String [] s = new String[lista1.size() + 1];
            String numero = String.valueOf(p.getID());

            s [0] = numero;
            s [1] = p.getUsuario();

            modeloTabla.addRow(s);

        }


        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    MenuPrincipal menuPrincipal = new MenuPrincipal();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


    }


}
