package com.example.proyectotfgdam.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class Contenido(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val titulo: Int,
    @StringRes val anio: Int,
    @StringRes val genero: Int,
    @StringRes val sinopsis: Int,
    @StringRes val temporadas: Int,
    val esFavorito: Boolean = false // Nuevo atributo, por defecto será 'false'
)

