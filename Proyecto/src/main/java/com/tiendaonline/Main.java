package com.tiendaonline;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.tiendaonline.controladores.UsuarioControlador;
import com.tiendaonline.controladores.ProductoControlador;
import com.tiendaonline.controladores.CategoriaControlador;
import com.tiendaonline.controladores.MarcaControlador;
import com.tiendaonline.controladores.CarritoControlador;
import com.tiendaonline.controladores.ItemCarritoControlador;
import com.tiendaonline.controladores.PedidoControlador;
import com.tiendaonline.controladores.DetallePedidoControlador;
import com.tiendaonline.controladores.DireccionControlador;
import com.tiendaonline.controladores.MetodoPagoControlador;
import com.tiendaonline.controladores.EnvioControlador;
import com.tiendaonline.controladores.EstadoPedidoControlador;
import com.tiendaonline.controladores.ResenaProductoControlador;
import com.tiendaonline.controladores.InventarioControlador;
import com.tiendaonline.controladores.ProveedorControlador;
import com.tiendaonline.controladores.RolUsuarioControlador;
import com.tiendaonline.controladores.NotificacionControlador;
import com.tiendaonline.controladores.CuponDescuentoControlador;
import com.tiendaonline.controladores.DevolucionControlador;
import com.tiendaonline.controladores.FavoritoControlador;


public class Main {
    public static void main(String[] args) {
        port(4567);
        Gson gson = new Gson();
        UsuarioControlador.rutas(gson);
        ProductoControlador.rutas(gson);
        CategoriaControlador.rutas(gson);
        MarcaControlador.rutas(gson);
        CarritoControlador.rutas(gson);
        ItemCarritoControlador.rutas(gson);
        PedidoControlador.rutas(gson);
        DetallePedidoControlador.rutas(gson);
        DireccionControlador.rutas(gson);
        MetodoPagoControlador.rutas(gson);
        EnvioControlador.rutas(gson);
        EstadoPedidoControlador.rutas(gson);
        ResenaProductoControlador.rutas(gson);
        InventarioControlador.rutas(gson);
        ProveedorControlador.rutas(gson);
        RolUsuarioControlador.rutas(gson);
        NotificacionControlador.rutas(gson);
        CuponDescuentoControlador.rutas(gson);
        DevolucionControlador.rutas(gson);
        FavoritoControlador.rutas(gson);
    }
}
