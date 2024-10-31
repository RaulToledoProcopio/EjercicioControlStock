package org.example.Model

import jakarta.persistence.*
import java.util.*

// Entity indica que esta clase es una entidad JPA que se corresponde con una tabla de la base de datos

@Entity
@Table(name = "Productos")
data class Producto(

    // Define el campo 'id' como la clave primaria de la tabla
    @Id
    val id: String,

    // Mapea el campo 'categoria' a una columna de la tabla con restricciones
    @Column(name = "categoria", nullable = false, length = 10)
    val categoria: String,

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String,

    @Column(name = "descripcion")
    val descripcion: String? = null,

    @Column(name = "precio_sin_iva", nullable = false)
    val precioSinIva: Float,

    @Column(name = "precio_con_iva", nullable = false)
    val precioConIva: Float,

    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.DATE)  // Define que solo se almacenará la fecha (sin hora)
    val fechaAlta: Date,

    // Almacena la cantidad en stock del producto
    @Column(name = "stock", nullable = false)
    var stock: Int,

    // Relación muchos-a-uno con la entidad 'Proveedor'
    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)  // Define la clave foránea 'proveedor_id' en la tabla 'Productos'
    val proveedor: Proveedor
)
