package com.example.proyectotfgdam.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectotfgdam.data.Contenido

interface DB {
    fun clearAllTables()

}

@Database(entities = [Contenido::class], version = 1)
abstract class ContenidoDatabase: RoomDatabase(), DB {
    abstract fun contenidoDao(): ContenidoDao

    override fun clearAllTables() {
        /*
    Esta funcion se podría dejar vacía debido a que no se va a llamar nunca
    Está creada debido a que puede salir el bug que indica que necesitamos una funcion con la funcion ClearAllTables al compilar
     */
    }

}