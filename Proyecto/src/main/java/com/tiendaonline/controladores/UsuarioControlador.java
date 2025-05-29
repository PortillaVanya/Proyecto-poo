package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Usuario;
import com.tiendaonline.repositorios.UsuarioRepositorio;
import java.util.Collections;

public class UsuarioControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/usuarios";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(UsuarioRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return UsuarioRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Usuario obj = gson.fromJson(req.body(), Usuario.class);
            return gson.toJson(UsuarioRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Usuario obj = gson.fromJson(req.body(), Usuario.class);
            return UsuarioRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = UsuarioRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
