package com.example.proyectotfgdam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contenido")
data class Contenido(
    @PrimaryKey(autoGenerate = true) val contenidoId: Int = 0, // Clave primaria autogenerada
    val tipo: String, // 'pelicula' o 'serie'
    val titulo: String, // Título del contenido
    val anio: Int, // Año de lanzamiento
    val genero: String, // Género del contenido
    val sinopsis: String?, // Sinopsis (puede ser null)
    val temporadas: Int? // Solo para series (puede ser null para películas)
)

