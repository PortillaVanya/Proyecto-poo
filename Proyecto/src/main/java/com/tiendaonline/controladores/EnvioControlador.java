package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Envio;
import com.tiendaonline.repositorios.EnvioRepositorio;
import java.util.Collections;

public class EnvioControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/envios";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(EnvioRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return EnvioRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Envio obj = gson.fromJson(req.body(), Envio.class);
            return gson.toJson(EnvioRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Envio obj = gson.fromJson(req.body(), Envio.class);
            return EnvioRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = EnvioRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
