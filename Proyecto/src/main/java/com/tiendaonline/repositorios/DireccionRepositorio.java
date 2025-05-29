package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Direccion;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DireccionRepositorio {
    private static final List<Direccion> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Direccion> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Direccion> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Direccion agregar(Direccion obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Direccion> actualizar(int id, Direccion obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
