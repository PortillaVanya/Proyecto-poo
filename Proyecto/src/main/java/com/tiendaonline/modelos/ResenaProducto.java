package com.tiendaonline.modelos;

public class ResenaProducto {
    private int id;
    private String nombre;

    public ResenaProducto() {}

    public ResenaProducto(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
