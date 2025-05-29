package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.RolUsuario;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RolUsuarioRepositorio {
    private static final List<RolUsuario> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<RolUsuario> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<RolUsuario> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static RolUsuario agregar(RolUsuario obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<RolUsuario> actualizar(int id, RolUsuario obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
