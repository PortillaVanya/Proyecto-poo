package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Producto;
import com.tiendaonline.repositorios.ProductoRepositorio;
import java.util.Collections;

public class ProductoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/productos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(ProductoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return ProductoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Producto obj = gson.fromJson(req.body(), Producto.class);
            return gson.toJson(ProductoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Producto obj = gson.fromJson(req.body(), Producto.class);
            return ProductoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = ProductoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
