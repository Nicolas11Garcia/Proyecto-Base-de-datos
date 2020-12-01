package com.nico.basededatos.clases;

public class Total {
    private String nombre;
    private int cantidad;
    private int precio;
    private String fecha;
    private String vendedor;
    private int total;

    public Total(String nombre, int cantidad, int precio, String fecha, String vendedor, int total) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}


