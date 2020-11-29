package com.nico.basededatos;

import com.nico.basededatos.MenuPrincipal;
import com.nico.basededatos.MyConnection;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class VerInactivos extends JFrame{
    private JButton atrasButton;
    private JTable table1;
    private JPanel ProductosInactivo;
    private DefaultTableModel modeloTabla;

    public VerInactivos() throws SQLException {
        super("Menu");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,400);
        setLocationRelativeTo(null);
        add(ProductosInactivo);

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
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Producto");
        modeloTabla.addColumn("Producto");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Fecha de Desactivación");

        table1.setModel(modeloTabla);
        List<Inactivos> lista2= dao.verInactivos();
        for(Inactivos p : lista2) {
            String[] s = new String[lista2.size() + 4];
            String id = String.valueOf(p.getID());
            String id_produc = String.valueOf(p.getID_Producto());
            String precio = String.valueOf(p.getPrecio());

            s[0] = id_produc;
            s[1] = p.getProducto();
            s[2] = precio;
            s[3] = p.getDesactivado();

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
