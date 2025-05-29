package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.MetodoPago;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MetodoPagoRepositorio {
    private static final List<MetodoPago> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<MetodoPago> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<MetodoPago> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static MetodoPago agregar(MetodoPago obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<MetodoPago> actualizar(int id, MetodoPago obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
