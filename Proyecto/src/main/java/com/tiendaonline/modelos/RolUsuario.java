package com.tiendaonline.modelos;

public class RolUsuario {
    private int id;
    private String nombre;

    public RolUsuario() {}

    public RolUsuario(String nombre) {
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
