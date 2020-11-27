package com.nico.basededatos;

import javax.swing.*;
import java.sql.SQLException;

public class MenuPrincipal extends JFrame{
    private JPanel panelMenu;
    private JButton cerrarSesiónButton;
    private JButton ingresarProductoButton;
    private JButton buscarProductoButton;
    private JButton desactivarProductoButton;
    private JButton activarProductoButton;
    private JButton verProductosInactivosButton;
    private JButton verProductosActivosButton;
    private JButton cambiarContraseñaButton;
    private JButton cambiarUsernameButton;
    private JButton verLoVendidoEnButton;
    private JButton verTodosLosUsuariosButton;


    public MenuPrincipal() throws SQLException {
        super("Menu");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        add(panelMenu);

        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "123";
        MyConnection link = new MyConnection(ip,user,pass,db);




    }
}
