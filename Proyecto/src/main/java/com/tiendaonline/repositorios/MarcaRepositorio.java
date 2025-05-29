package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Marca;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MarcaRepositorio {
    private static final List<Marca> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Marca> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Marca> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Marca agregar(Marca obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Marca> actualizar(int id, Marca obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
