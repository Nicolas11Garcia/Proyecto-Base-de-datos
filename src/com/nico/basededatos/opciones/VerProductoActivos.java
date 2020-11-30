package com.nico.basededatos.opciones;

import com.nico.basededatos.clases.FullProducto;
import com.nico.basededatos.MyConnection.MyConnection;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class VerProductoActivos extends  JFrame{
    private JPanel verActivos;
    private JTable table1;
    private JButton atrasButton;

    private DefaultTableModel modeloTabla;

    public VerProductoActivos() throws SQLException {
        super("Ver Productos Activos");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(verActivos);

        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "123";
        MyConnection link = new MyConnection(ip,user,pass,db);

        Dao dao = new Dao(link);

        //Crear tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");

        table1.setModel(modeloTabla);

        List<FullProducto> lista = dao.VerProductosActivos();

        for(FullProducto p : lista){
            String [] s = new String[lista.size() + 1];
            String id = String.valueOf(p.getID());
            String precio = String.valueOf(p.getPrecio());

            s [0] = id;
            s [1] = p.getNombre();
            s [2] = precio;

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
