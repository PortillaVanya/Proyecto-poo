package com.tiendaonline.modelos;

public class Favorito {
    private int id;
    private String nombre;

    public Favorito() {}

    public Favorito(String nombre) {
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
