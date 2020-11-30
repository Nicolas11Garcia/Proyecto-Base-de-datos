package com.nico.basededatos;
import com.formdev.flatlaf.FlatDarkLaf;
import com.nico.basededatos.opciones.InterfazLogin;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        FlatDarkLaf.install();
        InterfazLogin interfazLogin = new InterfazLogin();
    }
}
