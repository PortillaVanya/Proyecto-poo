package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.ResenaProducto;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ResenaProductoRepositorio {
    private static final List<ResenaProducto> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<ResenaProducto> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<ResenaProducto> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static ResenaProducto agregar(ResenaProducto obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<ResenaProducto> actualizar(int id, ResenaProducto obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
