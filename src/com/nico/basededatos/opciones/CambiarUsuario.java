package com.nico.basededatos.opciones;

import com.nico.basededatos.clases.CambiarUsuarioAtributo;
import com.nico.basededatos.MyConnection.MyConnection;
import com.nico.basededatos.clases.Trabajador;
import com.nico.basededatos.dao.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CambiarUsuario extends JFrame{
    private JPanel CambiarUsuario;
    private JButton atrasButton;
    private JTextField textoUsuario;
    private JPasswordField textoPass;
    private JTextField textoNuevoUser;
    private JButton cambiarUsuarioButton;

    public CambiarUsuario() throws SQLException {
        super("Cambiar Nombre de Usuario");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        add(CambiarUsuario);

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

        cambiarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textoUsuario.getText();
                String pass = textoPass.getText();
                String nuevoUsuario = textoNuevoUser.getText();
                List<Trabajador> lista = dao.verUsuarios();
                int usuarioEncontrado = 0;
                int usuarioRepetido = 0;

                String sql = "SELECT * FROM trabajador WHERE username = '"+usuario+"' AND contraseña = SHA2('"+pass+"',0)";

                for(Trabajador l : lista){
                    if(l.getUsuario().equals(nuevoUsuario)){
                        usuarioRepetido = usuarioRepetido + 1;
                        break;
                    }
                }
                try {
                    Connection con = link.getCon();
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    if (resultSet.next()){
                        usuarioEncontrado = usuarioEncontrado + 1;

                        if(usuarioRepetido == 1){
                            JOptionPane.showMessageDialog(CambiarUsuario, "El nuevo usuario ya existe");
                            textoUsuario.setText(null);
                            textoPass.setText(null);
                            textoNuevoUser.setText(null);
                        }
                        else if(usuarioEncontrado == 1){
                            CambiarUsuarioAtributo cambiarUser = new CambiarUsuarioAtributo(usuario,pass,nuevoUsuario);
                            dao.CambiarUser(cambiarUser);
                            JOptionPane.showMessageDialog(CambiarUsuario,"Usuario cambiado con Exito");
                            textoUsuario.setText(null);
                            textoPass.setText(null);
                            textoNuevoUser.setText(null);
                        }
                    }else{
                        JOptionPane.showMessageDialog(CambiarUsuario,"El usuario o la contraseña son invalidos, porfavor intente nuevamente");
                        textoUsuario.setText(null);
                        textoPass.setText(null);
                        textoNuevoUser.setText(null);
                    }
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });



    }

}
