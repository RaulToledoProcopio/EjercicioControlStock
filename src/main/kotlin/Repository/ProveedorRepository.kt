package org.example.Repository

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.Model.Proveedor
import org.example.Model.Producto

// Clase que maneja la persistencia y operaciones CRUD para la entidad Proveedor

class ProveedorRepository {

    // Crea un EntityManagerFactory para conectarse a la base de datos "unidadMySQL"

    private val entityManagerFactory: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")

    // Metodo para obtener el proveedor de un producto dado su ID

    fun obtenerProveedorProducto(idProducto: String): Proveedor? {

        // Crea un EntityManager para manejar la conexión y la transacción
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        return try {
            // Busca el producto por su ID. Si existe, devuelve su proveedor; si no, devuelve null
            val producto = entityManager.find(Producto::class.java, idProducto)
            producto?.proveedor
        } finally {
            entityManager.close()
        }
    }

    // Metodo para obtener todos los proveedores de la base de datos

    fun obtenerTodosProveedores(): List<Proveedor> {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        return try {
            entityManager.createQuery("FROM Proveedor", Proveedor::class.java).resultList
        } finally {
            entityManager.close()
        }
    }
}