package com.nico.basededatos;

public class Contrasena {
    private String user;
    private String pass;
    private String newContra;


    public Contrasena(String user, String pass, String newPass) {
        this.user = user;
        this.pass = pass;
        this.newContra = newPass;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNewPass() {
        return newContra;
    }

    public void setNewPass(String newPass) {
        this.newContra = newPass;
    }

}
