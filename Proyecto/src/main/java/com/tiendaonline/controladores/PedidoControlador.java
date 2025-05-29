package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Pedido;
import com.tiendaonline.repositorios.PedidoRepositorio;
import java.util.Collections;

public class PedidoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/pedidos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(PedidoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return PedidoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Pedido obj = gson.fromJson(req.body(), Pedido.class);
            return gson.toJson(PedidoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Pedido obj = gson.fromJson(req.body(), Pedido.class);
            return PedidoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = PedidoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
