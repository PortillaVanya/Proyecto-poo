package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Direccion;
import com.tiendaonline.repositorios.DireccionRepositorio;
import java.util.Collections;

public class DireccionControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/direccions";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(DireccionRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return DireccionRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Direccion obj = gson.fromJson(req.body(), Direccion.class);
            return gson.toJson(DireccionRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Direccion obj = gson.fromJson(req.body(), Direccion.class);
            return DireccionRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = DireccionRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
