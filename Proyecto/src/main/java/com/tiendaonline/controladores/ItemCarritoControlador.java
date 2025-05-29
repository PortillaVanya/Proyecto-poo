package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.ItemCarrito;
import com.tiendaonline.repositorios.ItemCarritoRepositorio;
import java.util.Collections;

public class ItemCarritoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/itemcarritos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(ItemCarritoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return ItemCarritoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            ItemCarrito obj = gson.fromJson(req.body(), ItemCarrito.class);
            return gson.toJson(ItemCarritoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            ItemCarrito obj = gson.fromJson(req.body(), ItemCarrito.class);
            return ItemCarritoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = ItemCarritoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
