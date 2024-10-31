package org.example

import org.example.Repository.ProductoRepository
import org.example.Repository.ProveedorRepository
import org.example.Repository.UsuarioRepository
import org.example.Service.ProductoService
import org.example.Service.ProveedorService
import org.example.Service.UsuarioService

fun main() {

    var opcion: Int? = null // Inicialización de la variable que almacenará la opción del menú.
    val productoService = ProductoService(ProductoRepository())
    val proveedorService = ProveedorService(ProveedorRepository())
    val usuarioRepository = UsuarioRepository()
    val usuarioService = UsuarioService(usuarioRepository)

    // Creación manual del usuario
    val nombre = "Raul"
    val password = "Toledo"
    usuarioService.crearUsuario(nombre, password)

    // Intento de login
    var loginExitoso = false
    while (!loginExitoso) {
        println("Por favor, ingrese su usuario:")
        val usuarioInput = readLine()
        println("Por favor, ingrese su contraseña:")
        val passwordInput = readLine()

        // Validación del usuario
        loginExitoso = usuarioService.validarUsuario(usuarioInput ?: "", passwordInput ?: "")
        if (!loginExitoso) {
            println("Login incorrecto. Intente de nuevo.")
        }
    }

    // Menú principal
    while (opcion != 0) { // Bucle que se ejecuta mientras la opción no sea 0.

        println("Menú:")
        println("1. Alta de producto")
        println("2. Baja de producto")
        println("3. Modificar nombre")
        println("4. Modificar stock")
        println("5. Obtener producto por ID")
        println("6. Obtener productos con stock")
        println("7. Obtener productos sin stock")
        println("8. Obtener el proveedor de un producto")
        println("9. Obtener la lista de proveedores")
        println("0. Salir")

        print("Elige una opción: ")

        opcion = readLine()?.toIntOrNull() // Lee la entrada del usuario y la convierte a Int.

        // Estructura de control que ejecuta un bloque de código según la opción seleccionada.
        when (opcion) {
            1 -> {
                println("Alta de producto...")
                productoService.altaProducto()
            }

            2 -> {
                println("Baja de producto...")
                productoService.bajaProducto()
            }

            3 -> {
                println("Introduzca el producto a modificar:")
                productoService.modificarNombreProducto()
            }

            4 -> {
                println("Introduzca el producto a modificar:")
                productoService.modificarStockProducto()
            }

            5 -> {
                println("Introduzca el id del producto:")
                productoService.obtenerProductoId()
            }

            6 -> {
                println("Obteniendo los productos con stock...")
                productoService.obtenerProductoStock()
            }

            7 -> {
                println("Obteniendo los productos sin stock...")
                productoService.obtenerProductoSinStock()
            }

            8 -> {
                println("Introduzca el producto:")
                proveedorService.obtenerProveedorProducto()
            }

            9 -> {
                println("Obteniendo la lista de proveedores...")
                proveedorService.obtenerTodosProveedores()
            }

            0 -> println("Saliendo")
            else -> println("Elige una opción válida")
        }
    }
}