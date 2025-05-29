package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Notificacion;
import com.tiendaonline.repositorios.NotificacionRepositorio;
import java.util.Collections;

public class NotificacionControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/notificacions";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(NotificacionRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return NotificacionRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Notificacion obj = gson.fromJson(req.body(), Notificacion.class);
            return gson.toJson(NotificacionRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Notificacion obj = gson.fromJson(req.body(), Notificacion.class);
            return NotificacionRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = NotificacionRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
