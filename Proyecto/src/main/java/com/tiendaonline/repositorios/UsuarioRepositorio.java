package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Usuario;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioRepositorio {
    private static final List<Usuario> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Usuario> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Usuario> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Usuario agregar(Usuario obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Usuario> actualizar(int id, Usuario obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
