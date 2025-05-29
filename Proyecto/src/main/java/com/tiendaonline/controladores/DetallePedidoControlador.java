package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.DetallePedido;
import com.tiendaonline.repositorios.DetallePedidoRepositorio;
import java.util.Collections;

public class DetallePedidoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/detallepedidos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(DetallePedidoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return DetallePedidoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            DetallePedido obj = gson.fromJson(req.body(), DetallePedido.class);
            return gson.toJson(DetallePedidoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            DetallePedido obj = gson.fromJson(req.body(), DetallePedido.class);
            return DetallePedidoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = DetallePedidoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
