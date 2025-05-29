package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.CuponDescuento;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CuponDescuentoRepositorio {
    private static final List<CuponDescuento> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<CuponDescuento> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<CuponDescuento> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static CuponDescuento agregar(CuponDescuento obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<CuponDescuento> actualizar(int id, CuponDescuento obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
