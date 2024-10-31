package org.example.Repository

import org.example.Model.Usuario

class UsuarioRepository {

    // Lista mutable que almacena los objetos de tipo Usuario
    private val usuarios = mutableListOf<Usuario>()

    // Metodo para crear un nuevo usuario y agregarlo a la lista

    fun crearUsuario(nombre: String, password: String) {
        val usuario = Usuario(nombre, password) // Crea una instancia de Usuario con los datos proporcionados
        usuarios.add(usuario) // Agrega el usuario a la lista 'usuarios'
    }

    // Metodo para validar el inicio de sesión de un usuario

    fun validarUsuario(nombre: String, password: String): Boolean {
        // Verifica si existe algún usuario en la lista que coincida con el nombre y la contraseña
        return usuarios.any { it.nombre == nombre && it.password == password }
    }
}
