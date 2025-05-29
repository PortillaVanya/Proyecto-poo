package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Usuario;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementación en memoria de IUsuarioRepositorio para pruebas.
 */
public class UsuarioRepositorioMemoria implements IUsuarioRepositorio {

    private final List<Usuario> almacenamiento = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger contador = new AtomicInteger(1);

    @Override
    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(almacenamiento);
    }

    @Override
    public Optional<Usuario> obtenerPorId(int id) {
        return almacenamiento.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public Usuario agregar(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setId(contador.getAndIncrement());
        }
        almacenamiento.add(usuario);
        return usuario;
    }

    @Override
    public boolean eliminar(int id) {
        return almacenamiento.removeIf(u -> u.getId() == id);
    }
}