package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Favorito;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FavoritoRepositorio {
    private static final List<Favorito> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Favorito> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Favorito> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Favorito agregar(Favorito obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Favorito> actualizar(int id, Favorito obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
