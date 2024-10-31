package org.example.Model

import jakarta.persistence.*

// Indica que esta clase es una entidad JPA que se corresponde con una tabla en la base de datos
@Entity
@Table(name = "Proveedores")
data class Proveedor(

    // Define el campo 'id' como clave primaria y utiliza una estrategia de generación automática de identidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Genera el valor de 'id' automáticamente
    val id: Long = 0,

    // Mapea el campo 'nombre' a una columna de la tabla;
    @Column(name = "nombre", unique = true, nullable = false, length = 50)
    val nombre: String,

    @Column(name = "direccion", nullable = false)
    val direccion: String,

    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL])
    val productos: List<Producto> = emptyList()  // Relaciona todos los productos asociados a este proveedor
)
