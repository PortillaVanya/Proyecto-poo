package com.tiendaonline.repositorios;

import com.tiendaonline.modelos.Usuario;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz mínima para repositorios de Usuario.
 * Permite desacoplar el servicio de la implementación concreta.
 */
public interface IUsuarioRepositorio {
    List<Usuario> obtenerTodos();
    Optional<Usuario> obtenerPorId(int id);
    Usuario agregar(Usuario usuario);
    boolean eliminar(int id);
}