package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.Proveedor;
import com.tiendaonline.repositorios.ProveedorRepositorio;
import java.util.Collections;

public class ProveedorControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/proveedors";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(ProveedorRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return ProveedorRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            Proveedor obj = gson.fromJson(req.body(), Proveedor.class);
            return gson.toJson(ProveedorRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            Proveedor obj = gson.fromJson(req.body(), Proveedor.class);
            return ProveedorRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = ProveedorRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
