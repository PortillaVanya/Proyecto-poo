package com.tiendaonline.servicios;

import com.tiendaonline.modelos.Usuario;
import com.tiendaonline.repositorios.IUsuarioRepositorio;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de alto nivel para operaciones con usuarios.
 * Depende de una abstracción de repositorio para facilitar pruebas.
 */
public class UserService {

    private final IUsuarioRepositorio repositorio;

    public UserService(IUsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Usuario> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    public Optional<Usuario> obtenerPorId(int id) {
        return repositorio.obtenerPorId(id);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return repositorio.agregar(usuario);
    }

    public boolean eliminarUsuario(int id) {
        return repositorio.eliminar(id);
    }
}