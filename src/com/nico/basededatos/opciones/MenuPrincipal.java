package com.nico.basededatos.opciones;

import com.nico.basededatos.MyConnection.MyConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setSize(600,400);
        setLocationRelativeTo(null);
        add(panelMenu);

        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "123";
        MyConnection link = new MyConnection(ip,user,pass,db);

        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    InterfazLogin login = new InterfazLogin();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        ingresarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    IngresarProducto ingresarProducto = new IngresarProducto();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    BuscarProducto buscarProducto = new BuscarProducto();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        desactivarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    DesactivarProduto desactivarProduto = new DesactivarProduto();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        activarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    ActivarProducto activarProducto = new ActivarProducto();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        verProductosInactivosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    VerInactivos verInactivos = new VerInactivos();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        verProductosActivosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    VerProductoActivos verProductoActivos = new VerProductoActivos();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        cambiarContraseñaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    CambiarContraseña cambiarContraseña = new CambiarContraseña();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        cambiarUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    CambiarUsuario cambiarUsuario = new CambiarUsuario();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        verLoVendidoEnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    TotalVendido vendidoMes = new TotalVendido();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        verTodosLosUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    VerUser verUser = new VerUser();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });






    }
}
