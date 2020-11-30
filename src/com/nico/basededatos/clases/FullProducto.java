package com.nico.basededatos.clases;

public class FullProducto {
    private int ID;
    private String nombre;
    private int precio;

    public FullProducto(int ID, String nombre, int precio) {
        this.ID = ID;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
