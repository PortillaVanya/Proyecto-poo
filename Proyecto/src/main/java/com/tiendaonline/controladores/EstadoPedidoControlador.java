package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.EstadoPedido;
import com.tiendaonline.repositorios.EstadoPedidoRepositorio;
import java.util.Collections;

public class EstadoPedidoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/estadopedidos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(EstadoPedidoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return EstadoPedidoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            EstadoPedido obj = gson.fromJson(req.body(), EstadoPedido.class);
            return gson.toJson(EstadoPedidoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            EstadoPedido obj = gson.fromJson(req.body(), EstadoPedido.class);
            return EstadoPedidoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = EstadoPedidoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
