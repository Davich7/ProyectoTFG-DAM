package com.example.consultacontenido

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "contenidoDB"
        private const val DATABASE_VERSION = 1
        const val TABLE_CONTENIDO = "contenido"
        const val COLUMN_ID = "id"
        const val COLUMN_TITULO = "titulo"
        const val COLUMN_TIPO = "tipo"
        const val COLUMN_ACTOR_PRINCIPAL = "actor_principal"
        const val COLUMN_FAVORITO = "favorito"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_CONTENIDO (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITULO TEXT,
                $COLUMN_TIPO TEXT,
                $COLUMN_ACTOR_PRINCIPAL TEXT,
                $COLUMN_FAVORITO INTEGER
            )
        """.trimIndent()
        db.execSQL(createTable)

        // Insertar datos iniciales
        insertarDatosIniciales(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CONTENIDO")
        onCreate(db)
    }

    // Insertar datos iniciales en la tabla "contenido"
    private fun insertarDatosIniciales(db: SQLiteDatabase) {
        val contenidoInicial = listOf(
            ContentValues().apply {
                put(COLUMN_TITULO, "Inception")
                put(COLUMN_TIPO, "pelicula")
                put(COLUMN_ACTOR_PRINCIPAL, "Leonardo DiCaprio")
                put(COLUMN_FAVORITO, 0)
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "Breaking Bad")
                put(COLUMN_TIPO, "serie")
                put(COLUMN_ACTOR_PRINCIPAL, "Bryan Cranston")
                put(COLUMN_FAVORITO, 1)
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "The Wolf of Wall Street")
                put(COLUMN_TIPO, "pelicula")
                put(COLUMN_ACTOR_PRINCIPAL, "Leonardo DiCaprio")
                put(COLUMN_FAVORITO, 0)
            }
        )
        contenidoInicial.forEach { db.insert(TABLE_CONTENIDO, null, it) }
    }

    // Obtener todos los registros por tipo (película o serie)
    fun obtenerContenidoPorTipo(tipo: String): List<Contenido> {
        val lista = mutableListOf<Contenido>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_CONTENIDO WHERE $COLUMN_TIPO = ?", arrayOf(tipo))
        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Contenido(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO)),
                        tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO)),
                        actorPrincipal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACTOR_PRINCIPAL)),
                        favorito = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FAVORITO)) == 1
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    // Obtener lista de actores sin duplicados
    fun obtenerActores(): List<String> {
        val lista = mutableSetOf<String>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT DISTINCT $COLUMN_ACTOR_PRINCIPAL FROM $TABLE_CONTENIDO", null)
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACTOR_PRINCIPAL)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista.toList()
    }

    // Marcar/desmarcar un contenido como favorito
    fun actualizarFavorito(id: Int, favorito: Boolean) {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put(COLUMN_FAVORITO, if (favorito) 1 else 0)
        }
        db.update(TABLE_CONTENIDO, valores, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
}
