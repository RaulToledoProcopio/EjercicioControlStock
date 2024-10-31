package org.example.Model

import jakarta.persistence.*

// Indica que esta clase es una entidad JPA que se corresponde con una tabla en la base de datos
@Entity
@Table(name = "usuarios")
class Usuario(

    // Define el campo 'nombre' como la clave primaria de la tabla, mapeado a la columna 'nombre_usuario'
    @Id
    @Column(name = "nombre_usuario")
    val nombre: String,

    // Mapea el campo 'password' a una columna de la tabla;
    @Column(nullable = false, length = 20)
    var password: String
)
