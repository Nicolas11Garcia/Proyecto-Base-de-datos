package com.nico.basededatos;

import com.nico.basededatos.MenuPrincipal;
import com.nico.basededatos.MyConnection;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ActivarProducto extends JFrame {
    private JButton atrasButton;
    private JTextField activarText;
    private JButton activarButton;
    private JPanel ActivarProducto;


    public ActivarProducto() throws SQLException {
        super("Activar Producto");
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

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String idText = activarText.getText();
                    //Convertir en int y restringir acceso a solo numeros
                    JFormattedTextField textField = new JFormattedTextField(new Integer(idText));
                    Integer valor = (Integer) textField.getValue();

                    List<IDproducto> lista = dao.IDdeProductos();
                    int idProductoEncontrado = 0;

                    for(IDproducto p : lista){
                        if(p.getId() == valor){
                            idProductoEncontrado = idProductoEncontrado + 1;
                            break;
                        }
                    }
                    if(idProductoEncontrado == 1){
                        IDproducto iDproducto = new IDproducto(valor);
                        dao.ActivarProducto(iDproducto);
                        JOptionPane.showMessageDialog(ActivarProducto,"Producto Activado Correctamente");
                        activarText.setText(null);
                    }else{
                        JOptionPane.showMessageDialog(ActivarProducto,"ID no encontrado");
                        activarText.setText(null);
                    }
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(null,"Ingrese un ID valido");
                        activarText.setText(null);
                }
            }
        });

    }
}
