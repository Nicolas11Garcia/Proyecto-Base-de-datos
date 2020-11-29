package com.nico.basededatos;

public class FechaDesdeHasta {
    private String fechaDesde;
    private String fechaHasta;

    public FechaDesdeHasta(String fechaDesde, String fechaHasta) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}
