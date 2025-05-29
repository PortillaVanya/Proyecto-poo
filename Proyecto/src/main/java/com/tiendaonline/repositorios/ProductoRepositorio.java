package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Producto;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductoRepositorio {
    private static final List<Producto> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Producto> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Producto> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Producto agregar(Producto obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Producto> actualizar(int id, Producto obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
