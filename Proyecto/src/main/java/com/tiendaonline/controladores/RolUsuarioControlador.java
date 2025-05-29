package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.RolUsuario;
import com.tiendaonline.repositorios.RolUsuarioRepositorio;
import java.util.Collections;

public class RolUsuarioControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/rolusuarios";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(RolUsuarioRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return RolUsuarioRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            RolUsuario obj = gson.fromJson(req.body(), RolUsuario.class);
            return gson.toJson(RolUsuarioRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            RolUsuario obj = gson.fromJson(req.body(), RolUsuario.class);
            return RolUsuarioRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = RolUsuarioRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
