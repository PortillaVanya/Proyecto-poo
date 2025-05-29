package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.ItemCarrito;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemCarritoRepositorio {
    private static final List<ItemCarrito> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<ItemCarrito> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<ItemCarrito> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static ItemCarrito agregar(ItemCarrito obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<ItemCarrito> actualizar(int id, ItemCarrito obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
