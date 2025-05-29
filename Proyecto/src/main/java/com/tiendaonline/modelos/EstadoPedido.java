package com.tiendaonline.modelos;

public class EstadoPedido {
    private int id;
    private String nombre;

    public EstadoPedido() {}

    public EstadoPedido(String nombre) {
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
