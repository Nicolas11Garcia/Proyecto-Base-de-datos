package com.nico.basededatos.opciones;

import com.nico.basededatos.clases.FechaDesdeHasta;
import com.nico.basededatos.MyConnection.MyConnection;
import com.nico.basededatos.clases.Total;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TotalVendido extends JFrame {
    private JPanel VendidoMes;
    private JButton atrasButton;
    private JTextField desdeFecha;
    private JTextField hastaFecha;
    private JButton verButton;
    private JTable table1;

    private DefaultTableModel modeloTabla;


    public TotalVendido() throws SQLException {
        super("Total Vendido");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(VendidoMes);

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
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Precio");
        table1.setModel(modeloTabla);

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

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    modeloTabla = new DefaultTableModel();
                    modeloTabla.addColumn("Nombre");
                    modeloTabla.addColumn("Fecha");
                    modeloTabla.addColumn("Precio");
                    table1.setModel(modeloTabla);

                    String desde = desdeFecha.getText();
                    String hasta = hastaFecha.getText();

                    if(desdeFecha.getText().equals("") || hastaFecha.getText().equals("")){
                        JOptionPane.showMessageDialog(VendidoMes,"Porfavor ingrese una fecha valida");
                    }else{
                        FechaDesdeHasta fecha = new FechaDesdeHasta(desde,hasta);
                        List<Total> lista = dao.totalVendido(fecha);

                        for(Total p : lista) {
                            String[] s = new String[lista.size() + 3];
                            String nombre = p.getNombre();
                            String fechaProducto = p.getFecha();
                            String precio = String.valueOf(p.getTotal());

                            s[0] = nombre;
                            s[1] = fechaProducto;
                            s[2] = precio;

                            modeloTabla.addRow(s);
                        }
                    }



            }
        });


    }
}
