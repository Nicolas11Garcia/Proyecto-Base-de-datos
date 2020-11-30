package com.nico.basededatos.opciones;

import com.nico.basededatos.clases.Contrasena;
import com.nico.basededatos.MyConnection.MyConnection;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CambiarContraseña extends JFrame{
    private JButton atrasButton;
    private JTextField cambiarpassUsuarioText;
    private JTextField cambiarPassText;
    private JTextField nuevaPassText;
    private JButton cambiarButton;
    private JPanel cambiarContraseña;


    public CambiarContraseña() throws SQLException {
        super("Cambiar Contraseña");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(cambiarContraseña);

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

        cambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = cambiarpassUsuarioText.getText();
                String pass = cambiarPassText.getText();
                String nuevaPass = nuevaPassText.getText();

                int usuarioEncontrado = 0;
                String sql = "SELECT * FROM trabajador WHERE username = '"+usuario+"' AND contraseña = SHA2('"+pass+"',0)";
                try{
                    Connection con = link.getCon();
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    if (resultSet.next()){
                        usuarioEncontrado = usuarioEncontrado + 1;
                        if(usuarioEncontrado == 1){
                            Contrasena cambiarPass = new Contrasena(usuario,pass,nuevaPass);
                            dao.CambiarContrasena(cambiarPass);
                            JOptionPane.showMessageDialog(cambiarContraseña,"Contraseña cambiada con Exito");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(cambiarContraseña,"El usuario o la contraseña son invalidos, porfavor intente nuevamente");
                    }
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                /*
                List<Trabajador> lista = dao.verUsuarios();
                for(Trabajador p : lista){
                    if(p.getUsuario().equals(usuario)){
                        usuarioEncontrado = usuarioEncontrado + 1;
                        break;
                    }
                }
                if(usuarioEncontrado == 1){
                    Contrasena cambiarPass = new Contrasena(usuario,pass,nuevaPass);
                    dao.CambiarContrasena(cambiarPass);
                    JOptionPane.showMessageDialog(cambiarContraseña,"Contraseña cambiada con Exito");
                    cambiarpassUsuarioText.setText(null);
                    cambiarPassText.setText(null);
                    nuevaPassText.setText(null);
                }
                else{
                    JOptionPane.showMessageDialog(cambiarContraseña,"El usuario o la contraseña son incorrectos, porfavor intente denuevo");
                }
                 */





            }
        });


    }
}
