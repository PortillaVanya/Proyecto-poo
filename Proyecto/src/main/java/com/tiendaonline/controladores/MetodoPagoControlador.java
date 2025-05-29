package com.tiendaonline.controladores;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.modelos.MetodoPago;
import com.tiendaonline.repositorios.MetodoPagoRepositorio;
import java.util.Collections;

public class MetodoPagoControlador {
    public static void rutas(Gson gson) {
        String rutaBase = "/api/metodopagos";
        get(rutaBase, (req, res) -> {
            res.type("application/json");
            return gson.toJson(MetodoPagoRepositorio.obtenerTodos());
        });

        get(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            return MetodoPagoRepositorio.obtenerPorId(Integer.parseInt(req.params("id")))
                .map(gson::toJson).orElse("{}");
        });

        post(rutaBase, (req, res) -> {
            res.type("application/json");
            MetodoPago obj = gson.fromJson(req.body(), MetodoPago.class);
            return gson.toJson(MetodoPagoRepositorio.agregar(obj));
        });

        put(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            MetodoPago obj = gson.fromJson(req.body(), MetodoPago.class);
            return MetodoPagoRepositorio.actualizar(Integer.parseInt(req.params("id")), obj)
                .map(gson::toJson).orElse("{}");
        });

        delete(rutaBase + "/:id", (req, res) -> {
            res.type("application/json");
            boolean ok = MetodoPagoRepositorio.eliminar(Integer.parseInt(req.params("id")));
            return gson.toJson(Collections.singletonMap("deleted", ok));
        });
    }
}
