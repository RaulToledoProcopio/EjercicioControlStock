package org.example.Service

import org.example.Repository.UsuarioRepository

// Constructor que recibe un repositorio de usuarios para realizar operaciones sobre la base de datos.
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    // Metodo para crear un nuevo usuario.
    fun crearUsuario(nombre: String, password: String) {
        usuarioRepository.crearUsuario(nombre, password) // Llama al repositorio para registrar el usuario con el nombre y contraseña.
    }

    // Metodo para validar las credenciales de un usuario.
    fun validarUsuario(nombre: String, password: String): Boolean {
        return usuarioRepository.validarUsuario(nombre, password) // Llama al repositorio para verificar si las credenciales son correctas.
    }
}
