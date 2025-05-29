package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Pedido;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PedidoRepositorio {
    private static final List<Pedido> list = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static List<Pedido> obtenerTodos() {
        return new ArrayList<>(list);
    }

    public static Optional<Pedido> obtenerPorId(int id) {
        return list.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Pedido agregar(Pedido obj) {
        obj.setId(counter.getAndIncrement());
        list.add(obj);
        return obj;
    }

    public static Optional<Pedido> actualizar(int id, Pedido obj) {
        return obtenerPorId(id).map(existing -> {
            existing.setNombre(obj.getNombre());
            return existing;
        });
    }

    public static boolean eliminar(int id) {
        return list.removeIf(e -> e.getId() == id);
    }
}
