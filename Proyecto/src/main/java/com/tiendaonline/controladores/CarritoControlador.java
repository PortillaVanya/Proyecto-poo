package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Carrito;
import com.tiendaonline.repositorios.CarritoRepositorio;
import java.util.Collections;

public class CarritoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/carritos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(CarritoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return CarritoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Carrito obj = gson.fromJson(req.body(), Carrito.class);
            return gson.toJson(CarritoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Carrito obj = gson.fromJson(req.body(), Carrito.class);
            return CarritoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = CarritoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
