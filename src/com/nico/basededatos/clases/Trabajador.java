package com.nico.basededatos.clases;

public class Trabajador {
    private String usuario;
    private String pass;


    public Trabajador (String usuario, String pass){
        this.usuario = usuario;
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
