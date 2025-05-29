package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Notificacion;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class NotificacionRepositorio {
    private static final List<Notificacion> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Notificacion> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Notificacion> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Notificacion agregar(Notificacion obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Notificacion> actualizar(int id, Notificacion obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
