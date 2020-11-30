package com.nico.basededatos.opciones;

import com.nico.basededatos.IDproducto;
import com.nico.basededatos.MenuPrincipal;
import com.nico.basededatos.MyConnection.MyConnection;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class DesactivarProduto extends  JFrame{
    private JPanel panelDesactivar;
    private JButton atrasButton;
    private JTextField idTexto;
    private JButton desactivarButton;

    public DesactivarProduto() throws SQLException {
        super("Menu");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(panelDesactivar);

        // Conexion sql
        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "";
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

        desactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String idText = idTexto.getText();
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
                        dao.DesactivarProducto(iDproducto);
                        JOptionPane.showMessageDialog(panelDesactivar,"Producto Desactivado Correctamente");
                        idTexto.setText(null);
                    }else{
                        JOptionPane.showMessageDialog(panelDesactivar,"ID no encontrado");
                        idTexto.setText(null);
                    }
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(null,"Ingrese un ID valido");
                    idTexto.setText(null);
                }





            }
        });



    }
}
