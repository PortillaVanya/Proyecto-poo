package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.EstadoPedido;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EstadoPedidoRepositorio {
    private static final List<EstadoPedido> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<EstadoPedido> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<EstadoPedido> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static EstadoPedido agregar(EstadoPedido obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<EstadoPedido> actualizar(int id, EstadoPedido obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
