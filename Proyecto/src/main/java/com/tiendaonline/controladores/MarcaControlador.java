package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Marca;
import com.tiendaonline.repositorios.MarcaRepositorio;
import java.util.Collections;

public class MarcaControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/marcas";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(MarcaRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return MarcaRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Marca obj = gson.fromJson(req.body(), Marca.class);
            return gson.toJson(MarcaRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Marca obj = gson.fromJson(req.body(), Marca.class);
            return MarcaRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = MarcaRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
