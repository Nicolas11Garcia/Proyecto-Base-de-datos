package com.nico.basededatos;

import javax.swing.*;

public class InterfazLogin extends JFrame{
    private JTextField textoUsuario;
    private JPasswordField textoPass;
    private JButton entrarButton;
    private JPanel panelLogin;

    public InterfazLogin(){
        super("Login");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        add(panelLogin);
    }
}
