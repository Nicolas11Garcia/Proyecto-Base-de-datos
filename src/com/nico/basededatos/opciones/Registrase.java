package com.nico.basededatos.opciones;

import com.nico.basededatos.MyConnection.MyConnection;
import com.nico.basededatos.clases.Trabajador;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Registrase extends JFrame{
    private JPanel panelRegistrarse;
    private JTextField usuarioNew;
    private JPasswordField passNew;
    private JButton registrarseButton;
    private JButton atrasButton;

    public Registrase() throws SQLException {
        super("Registrarse");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        add(panelRegistrarse);

        String ip = "localhost";
        int port = 3306;
        String db = "botilleria";
        String user = "root";
        String pass = "123";
        MyConnection link = new MyConnection(ip,user,pass,db);

        Dao registrarse = new Dao(link);



        atrasButton.addActionListener(new ActionListener() {
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

        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevousuario = usuarioNew.getText();
                String nuevaPass = passNew.getText();
                int usuarioEncontrado = 0;
                List<Trabajador> lista = registrarse.verUsuarios();

                for(Trabajador t : lista){
                    if(t.getUsuario().equals(nuevousuario)){
                        usuarioEncontrado = usuarioEncontrado + 1;
                        JOptionPane.showMessageDialog(null,"El usuario que desea ingresar ya existe, porfavor ingrese otro nuevamente");
                        break;
                    }
                }
                if(usuarioEncontrado == 0){
                    if(usuarioNew.getText().equals("") || passNew.getText().equals("")){
                        JOptionPane.showMessageDialog(panelRegistrarse,"Porfavor ingrese un usuario o contraseña validos");}
                    else{
                        Trabajador trabajador = new Trabajador(nuevousuario,nuevaPass);
                        registrarse.AgregarUser(trabajador);
                        JOptionPane.showMessageDialog(panelRegistrarse,"Usuario Ingresado");}
                        usuarioNew.setText(null);
                        passNew.setText(null);
                }
                }

        });





    }
}
