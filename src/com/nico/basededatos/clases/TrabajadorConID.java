package com.nico.basededatos.clases;

import javax.swing.*;

public class TrabajadorConID{
    private int ID;
    private String usuario;

    public TrabajadorConID(int ID, String usuario) {
        this.ID = ID;
        this.usuario = usuario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
