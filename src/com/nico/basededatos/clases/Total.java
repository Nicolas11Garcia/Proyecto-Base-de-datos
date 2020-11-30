package com.nico.basededatos.clases;

public class Total {
    private String nombre;
    private String fecha;
    private int total;

    public Total(String nombre, String fecha, int total) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
