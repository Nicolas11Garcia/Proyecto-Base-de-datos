package com.nico.basededatos;

import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;


public class BuscarProducto extends JFrame{
    private JButton atrasButton;
    private JTextField buscarText;
    private JButton buscarButton;
    private JPanel buscarProducto;


    public BuscarProducto() throws SQLException {
        super("Menu");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(buscarProducto);

        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "123";
        MyConnection link = new MyConnection(ip,user,pass,db);
        Dao daoBuscar = new Dao(link);


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

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String idText = buscarText.getText();
                    //Convertir en int y restringir acceso a solo numeros
                    JFormattedTextField textField = new JFormattedTextField(new Integer(idText));
                    Integer valor = (Integer) textField.getValue();

                    //Se crea la clase IDproducto para daoBuscar lo reciba
                    IDproducto buscar = new IDproducto(valor);

                    int idProductoEncontrado = 0;
                    //Lista
                    List<IDproducto> lista = daoBuscar.IDdeProductos();

                    for(IDproducto p : lista){
                        if(p.getId() == valor){
                            idProductoEncontrado = idProductoEncontrado + 1;
                            break;
                        }
                    }

                    if(idProductoEncontrado == 1){
                        JOptionPane.showMessageDialog(buscarProducto,"El Producto Buscado: " + daoBuscar.buscarProducto(buscar));
                        buscarText.setText(null);
                    }else{
                        JOptionPane.showMessageDialog(buscarProducto,"Producto no encontrado");
                        buscarText.setText(null);
                    }
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(null,"Ingrese un ID valido");
                    buscarText.setText(null);
                }





            }
        });




    }
}
