package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.DetallePedido;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DetallePedidoRepositorio {
    private static final List<DetallePedido> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<DetallePedido> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<DetallePedido> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static DetallePedido agregar(DetallePedido obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<DetallePedido> actualizar(int id, DetallePedido obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
