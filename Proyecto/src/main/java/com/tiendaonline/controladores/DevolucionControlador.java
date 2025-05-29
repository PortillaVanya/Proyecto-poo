package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Devolucion;
import com.tiendaonline.repositorios.DevolucionRepositorio;
import java.util.Collections;

public class DevolucionControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/devolucions";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(DevolucionRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return DevolucionRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Devolucion obj = gson.fromJson(req.body(), Devolucion.class);
            return gson.toJson(DevolucionRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Devolucion obj = gson.fromJson(req.body(), Devolucion.class);
            return DevolucionRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = DevolucionRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
