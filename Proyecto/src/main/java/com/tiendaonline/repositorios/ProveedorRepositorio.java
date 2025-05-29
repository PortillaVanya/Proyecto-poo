package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Proveedor;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProveedorRepositorio {
    private static final List<Proveedor> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Proveedor> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Proveedor> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Proveedor agregar(Proveedor obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Proveedor> actualizar(int id, Proveedor obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
