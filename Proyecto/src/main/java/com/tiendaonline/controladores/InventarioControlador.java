package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Inventario;
import com.tiendaonline.repositorios.InventarioRepositorio;
import java.util.Collections;

public class InventarioControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/inventarios";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(InventarioRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return InventarioRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Inventario obj = gson.fromJson(req.body(), Inventario.class);
            return gson.toJson(InventarioRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Inventario obj = gson.fromJson(req.body(), Inventario.class);
            return InventarioRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = InventarioRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
