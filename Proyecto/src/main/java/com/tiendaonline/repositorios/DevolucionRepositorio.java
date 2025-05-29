package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Devolucion;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DevolucionRepositorio {
    private static final List<Devolucion> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Devolucion> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Devolucion> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Devolucion agregar(Devolucion obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Devolucion> actualizar(int id, Devolucion obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
