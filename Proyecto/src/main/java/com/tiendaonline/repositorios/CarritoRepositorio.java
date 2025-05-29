package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Carrito;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CarritoRepositorio {
    private static final List<Carrito> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Carrito> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Carrito> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Carrito agregar(Carrito obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Carrito> actualizar(int id, Carrito obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
