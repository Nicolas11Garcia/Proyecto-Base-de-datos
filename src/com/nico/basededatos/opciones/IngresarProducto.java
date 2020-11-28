package com.nico.basededatos.opciones;

import com.nico.basededatos.MenuPrincipal;
import com.nico.basededatos.MyConnection;
import com.nico.basededatos.Producto;
import com.nico.basededatos.Trabajador;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class IngresarProducto extends  JFrame{
    private JPanel ingresarProductoPanel;
    private JButton atrasButton;
    private JButton ingresarButton;
    private JTextField textoNombre;
    private JTextField textoPrecio;


    public IngresarProducto() throws SQLException {
        super("Menu");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(ingresarProductoPanel);

        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "123";
        MyConnection link = new MyConnection(ip,user,pass,db);
        Dao dao = new Dao(link);


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

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreProducto = textoNombre.getText();
                String precioProducto = textoPrecio.getText();

                int productoEncontrado = 0;
                List<Producto> lista = dao.verProductos();

                for(Producto p : lista){
                    if(p.getNombre().equalsIgnoreCase(nombreProducto)){
                        productoEncontrado = productoEncontrado + 1;
                        JOptionPane.showMessageDialog(null,"No puede agregar productos repetidos");
                        textoNombre.setText(null);
                        textoPrecio.setText(null);
                        break;
                    }
                }

                if(productoEncontrado == 0){
                    if(nombreProducto.equals("") || precioProducto.equals("")){
                        JOptionPane.showMessageDialog(ingresarProductoPanel,"Porfavor ingrese un producto valido");

                    }

                    else{
                        int transformador = Integer.parseInt(precioProducto);
                        Producto producto = new Producto(nombreProducto,transformador);
                        dao.IngresarProducto(producto);
                        JOptionPane.showMessageDialog(ingresarProductoPanel,"Producto Ingresado Correctamente");
                        textoNombre.setText(null);
                        textoPrecio.setText(null);
                    }
                }




            }
        });




    }
}
