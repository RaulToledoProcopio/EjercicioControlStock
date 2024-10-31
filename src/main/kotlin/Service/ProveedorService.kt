package org.example.Service

import org.example.Model.Proveedor
import org.example.Repository.ProveedorRepository

// Constructor que recibe un repositorio de proveedores para realizar operaciones sobre la base de datos.
class ProveedorService(private val proveedorRepository: ProveedorRepository) {

    // Metodo para obtener el proveedor de un producto específico.
    fun obtenerProveedorProducto(): Proveedor? {
        println("Introduce el ID del producto para obtener su proveedor:")
        val idProducto = readLine() ?: return null

        // Llama al repositorio para obtener el proveedor del producto y devuelve el resultado.
        return proveedorRepository.obtenerProveedorProducto(idProducto)
    }

    // Metodo para obtener una lista de todos los proveedores.
    fun obtenerTodosProveedores(): List<Proveedor> {
        return proveedorRepository.obtenerTodosProveedores() // Llama al repositorio para obtener todos los proveedores.
    }
}