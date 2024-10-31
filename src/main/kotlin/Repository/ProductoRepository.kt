package org.example.Repository

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.example.Model.Producto

// Clase que actúa como repositorio para la entidad Producto, maneja la persistencia y las operaciones CRUD
class ProductoRepository {

    // Crea una instancia de EntityManagerFactory para conectarse a la base de datos "unidadMySQL"
    private val entityManagerFactory: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")

    // Metodo para dar de alta un producto en la base de datos
    fun altaProducto(producto: Producto) {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()  // Crea un EntityManager
        try {
            entityManager.transaction.begin()  // Inicia la transacción
            entityManager.persist(producto)  // Persiste el objeto Producto en la base de datos
            entityManager.transaction.commit()  // Confirma la transacción
        } catch (e: Exception) {
            entityManager.transaction.rollback()  // Revierte la transacción en caso de error
            e.printStackTrace()
        } finally {
            entityManager.close()  // Cierra el EntityManager para liberar recursos
        }
    }

    // Metodo para dar de baja un producto

    fun bajaProducto(id: String): Boolean {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        return try {
            entityManager.transaction.begin()  // Inicia la transacción
            val producto = entityManager.find(Producto::class.java, id)  // Busca el producto por su ID
            if (producto != null) {
                entityManager.remove(producto)  // Elimina el producto si existe
                entityManager.transaction.commit()  // Confirma la transacción
                true
            } else {
                entityManager.transaction.rollback()  // Revierte la transacción si no se encuentra el producto
                false
            }
        } catch (e: Exception) {
            entityManager.transaction.rollback()
            e.printStackTrace()
            false
        } finally {
            entityManager.close()
        }
    }

    // Metodo para modificar el nombre de un producto

    fun modificarNombreProducto(id: String, nuevoNombre: String): Boolean {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        return try {
            entityManager.transaction.begin()
            val producto = entityManager.find(Producto::class.java, id)
            if (producto != null) {
                producto.nombre = nuevoNombre  // Cambia el nombre del producto
                entityManager.merge(producto)  // Actualiza el producto en la base de datos
                entityManager.transaction.commit()
                true
            } else {
                entityManager.transaction.rollback()
                false
            }
        } catch (e: Exception) {
            entityManager.transaction.rollback()
            e.printStackTrace()
            false
        } finally {
            entityManager.close()
        }
    }

    // Metodo para modificar el stock de un producto según su ID

    fun modificarStockProducto(id: String, nuevoStock: Int): Boolean {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        return try {
            entityManager.transaction.begin()
            val producto = entityManager.find(Producto::class.java, id)
            if (producto != null) {
                producto.stock = nuevoStock  // Cambia el stock del producto
                entityManager.merge(producto)  // Actualiza el producto en la base de datos
                entityManager.transaction.commit()
                true
            } else {
                entityManager.transaction.rollback()
                false
            }
        } catch (e: Exception) {
            entityManager.transaction.rollback()
            e.printStackTrace()
            false
        } finally {
            entityManager.close()
        }
    }

    // Metodo para obtener un producto por su ID

    fun obtenerProducto(id: String): Producto? {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        return try {
            entityManager.find(Producto::class.java, id)  // Busca y devuelve el producto si existe
        } finally {
            entityManager.close()
        }
    }

    // Metodo para obtener una lista de productos con stock

    fun obtenerProductosConStock(): List<Producto> {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        return try {
            //createQuery es un metodo de EntityManager que permite crear consultas usando JPQL
            entityManager.createQuery("FROM Producto WHERE stock > 0", Producto::class.java).resultList
        } finally {
            entityManager.close()
        }
    }

    // Metodo para obtener una lista de productos sin stock (stock igual a cero)

    fun obtenerProductosSinStock(): List<Producto> {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        return try {
            entityManager.createQuery("FROM Producto WHERE stock = 0", Producto::class.java).resultList
        } finally {
            entityManager.close()
        }
    }
}
