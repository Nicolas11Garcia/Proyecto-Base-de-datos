package com.nico.basededatos.clases;

import javax.swing.*;

public class Inactivos{
    private int ID;
    private int ID_Producto;
    private String Producto;
    private int Precio;
    private String desactivado;

     public Inactivos(int ID, int ID_Producto, String producto, int precio, String desactivado) {
        this.ID = ID;
        this.ID_Producto = ID_Producto;
        Producto = producto;
        Precio = precio;
        this.desactivado = desactivado;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public String getDesactivado() {
        return desactivado;
    }

    public void setDesactivado(String desactivado) {
        this.desactivado = desactivado;
    }
}
