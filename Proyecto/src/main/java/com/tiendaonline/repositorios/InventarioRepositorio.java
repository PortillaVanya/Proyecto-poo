package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Inventario;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InventarioRepositorio {
    private static final List<Inventario> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Inventario> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Inventario> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Inventario agregar(Inventario obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Inventario> actualizar(int id, Inventario obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
