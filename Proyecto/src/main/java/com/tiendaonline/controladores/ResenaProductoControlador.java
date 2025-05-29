package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.ResenaProducto;
import com.tiendaonline.repositorios.ResenaProductoRepositorio;
import java.util.Collections;

public class ResenaProductoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/resenaproductos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(ResenaProductoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return ResenaProductoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            ResenaProducto obj = gson.fromJson(req.body(), ResenaProducto.class);
            return gson.toJson(ResenaProductoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            ResenaProducto obj = gson.fromJson(req.body(), ResenaProducto.class);
            return ResenaProductoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = ResenaProductoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
