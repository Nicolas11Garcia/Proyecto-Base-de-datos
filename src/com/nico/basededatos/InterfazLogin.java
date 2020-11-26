package com.nico.basededatos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InterfazLogin extends JFrame{
    private JTextField textoUsuario;
    private JPasswordField textoPass;
    private JButton entrarButton;
    private JPanel panelLogin;
    private JButton registarseButton;

    public InterfazLogin() throws SQLException {
        super("Login");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        add(panelLogin);

        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "123";
        MyConnection link = new MyConnection(ip,user,pass,db);



        //Registarse
        registarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                try {
                    Registrase registraseOpcion = new Registrase();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });








    }
}
