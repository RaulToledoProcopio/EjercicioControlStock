package org.example.Service

import org.example.Model.Producto
import org.example.Model.Proveedor
import org.example.Repository.ProductoRepository
import java.util.*

// Constructor que recibe un repositorio de productos para realizar operaciones sobre la base de datos.
class ProductoService(private val productoRepository: ProductoRepository) {

    // Metodo para dar de alta un nuevo producto.
    fun altaProducto() {

        println("Introduce la categoría del producto:")
        val categoria = readLine() ?: return

        println("Introduce el nombre del producto:")
        val nombre = readLine() ?: return

        println("Introduce una descripción del producto (opcional):")
        val descripcion = readLine()

        println("Introduce el precio sin IVA del producto:")
        val precioSinIva = readLine()?.toFloatOrNull() ?: return

        println("Introduce el nombre del proveedor:")
        val nombreProveedor = readLine() ?: return

        println("Introduce la dirección del proveedor:")
        val direccionProveedor = readLine() ?: return

        // Crea un objeto Proveedor con ID autogenerado (0).
        val proveedor = Proveedor(0, nombreProveedor, direccionProveedor)
        altaProducto(
            categoria,
            nombre,
            descripcion,
            precioSinIva,
            proveedor
        )
    }

    // Metodo privado para crear un producto y guardarlo en el repositorio.
    private fun altaProducto(
        categoria: String,
        nombre: String,
        descripcion: String?,
        precioSinIva: Float,
        proveedor: Proveedor
    ) {
        val id = generateProductId(categoria, nombre, proveedor.nombre) // Genera un ID para el producto.
        val precioConIva = precioSinIva * 1.21f // Calcula el precio con IVA.
        val fechaAlta = Date() // Obtiene la fecha actual.

        // Crea un objeto Producto con la información recolectada.
        val producto =
            Producto(id, categoria, nombre, descripcion, precioSinIva, precioConIva, fechaAlta, 10, proveedor)

        productoRepository.altaProducto(producto) // Guarda el producto en el repositorio.
        println("Producto añadido: $producto")
    }

    // Metodo para eliminar un producto.
    fun bajaProducto() {
        println("Introduce el ID del producto a eliminar:")
        val id = readLine() ?: return // Lee el ID del producto a eliminar.

        if (productoRepository.bajaProducto(id)) {
            println("Producto eliminado")
        } else {
            println("No se ha podido eliminar el producto.")
        }
    }

    // Metodo para modificar el nombre de un producto existente.
    fun modificarNombreProducto() {
        println("Introduce el ID del producto a modificar:")
        val id = readLine() ?: return
        println("Introduce el nuevo nombre del producto:")
        val nuevoNombre = readLine() ?: return

        // Llama al repositorio para modificar el nombre y muestra el resultado.
        if (productoRepository.modificarNombreProducto(id, nuevoNombre)) {
            println("Nombre modificado.")
        } else {
            println("No se ha podido modificar el nombre del producto.")
        }
    }

    // Metodo para modificar el stock de un producto existente.
    fun modificarStockProducto() {
        println("Introduce el ID del producto a modificar:")
        val id = readLine() ?: return
        println("Introduce el nuevo stock del producto:")
        val nuevoStock = readLine()?.toIntOrNull() ?: return

        // Llama al repositorio para modificar el stock y muestra el resultado.
        if (productoRepository.modificarStockProducto(id, nuevoStock)) {
            println("Stock modificado")
        } else {
            println("No se ha podido modificar el stock del producto")
        }
    }

    // Metodo para obtener un producto por su ID.
    fun obtenerProductoId() {
        println("Introduce el ID del producto:")
        val id = readLine() ?: return

        // Llama al repositorio para obtener el producto.
        val producto = productoRepository.obtenerProducto(id)
        if (producto != null) {
            println("Producto obtenido: $producto")
        } else {
            println("No se encontró el producto con el ID proporcionado")
        }
    }

    // Metodo para listar productos que tienen stock disponible.
    fun obtenerProductoStock() {
        val productos = productoRepository.obtenerProductosConStock() // Obtiene la lista de productos con stock.
        if (productos.isNotEmpty()) {
            println("Productos con stock:")
            productos.forEach { println(it) } // Muestra cada producto con stock.
        } else {
            println("No hay productos con stock")
        }
    }

    // Metodo para listar productos que no tienen stock.
    fun obtenerProductoSinStock() {
        val productos = productoRepository.obtenerProductosSinStock()
        if (productos.isNotEmpty()) {
            println("Productos sin stock")
            productos.forEach { println(it) }
        } else {
            println("No hay productos sin stock.")
        }
    }

    // Metodo privado para generar un ID único para el producto basado en la categoría, nombre y proveedor.
    private fun generateProductId(categoria: String, nombre: String, proveedor: String): String {
        // Con el .take(3) conseguimos que el id se cree con las 3 primeras letras de cada campo.
        return categoria.take(3) + nombre.take(3) + proveedor.take(3)
    }

}