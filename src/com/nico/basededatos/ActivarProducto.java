package com.nico.basededatos;

import com.nico.basededatos.MenuPrincipal;
import com.nico.basededatos.MyConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ActivarProducto extends JFrame {
    private JButton atrasButton;
    private JTextField activarText;
    private JButton activarButton;
    private JPanel ActivarProducto;


    public ActivarProducto() throws SQLException {
        super("Activar  Producto");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(ActivarProducto);

        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "";
        MyConnection link = new MyConnection(ip,user,pass,db);

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
