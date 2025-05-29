package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Categoria;
import com.tiendaonline.repositorios.CategoriaRepositorio;
import java.util.Collections;

public class CategoriaControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/categorias";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(CategoriaRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return CategoriaRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Categoria obj = gson.fromJson(req.body(), Categoria.class);
            return gson.toJson(CategoriaRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Categoria obj = gson.fromJson(req.body(), Categoria.class);
            return CategoriaRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = CategoriaRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
