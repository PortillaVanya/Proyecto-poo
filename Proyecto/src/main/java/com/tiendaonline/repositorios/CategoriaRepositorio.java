package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Categoria;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoriaRepositorio {
    private static final List<Categoria> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Categoria> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Categoria> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Categoria agregar(Categoria obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Categoria> actualizar(int id, Categoria obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
