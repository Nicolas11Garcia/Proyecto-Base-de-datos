package com.nico.basededatos;

public class CambiarUsuarioAtributo {
    private  String usuario;
    private String contraseña;
    private String nuevoUsuario;

    public CambiarUsuarioAtributo(String usuario, String contraseña, String nuevoUsuario) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nuevoUsuario = nuevoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(String nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }
}
