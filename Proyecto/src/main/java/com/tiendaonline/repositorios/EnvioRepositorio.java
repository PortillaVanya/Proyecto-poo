package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Envio;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EnvioRepositorio {
    private static final List<Envio> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Envio> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Envio> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Envio agregar(Envio obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Envio> actualizar(int id, Envio obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
