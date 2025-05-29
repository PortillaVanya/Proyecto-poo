package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.CuponDescuento;
import com.tiendaonline.repositorios.CuponDescuentoRepositorio;
import java.util.Collections;

public class CuponDescuentoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/cupondescuentos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(CuponDescuentoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return CuponDescuentoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            CuponDescuento obj = gson.fromJson(req.body(), CuponDescuento.class);
            return gson.toJson(CuponDescuentoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            CuponDescuento obj = gson.fromJson(req.body(), CuponDescuento.class);
            return CuponDescuentoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = CuponDescuentoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
