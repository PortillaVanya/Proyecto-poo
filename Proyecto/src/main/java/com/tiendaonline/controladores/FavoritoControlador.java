package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Favorito;
import com.tiendaonline.repositorios.FavoritoRepositorio;
import java.util.Collections;

public class FavoritoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/favoritos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(FavoritoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return FavoritoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Favorito obj = gson.fromJson(req.body(), Favorito.class);
            return gson.toJson(FavoritoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Favorito obj = gson.fromJson(req.body(), Favorito.class);
            return FavoritoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = FavoritoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
