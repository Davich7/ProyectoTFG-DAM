package com.example.proyectotfgdam.data.database

import androidx.room.*
import com.example.proyectotfgdam.data.Contenido
import kotlinx.coroutines.flow.Flow

@Dao
interface ContenidoDao {

    // Obtener todos los contenidos
    @Query("SELECT * FROM contenido")
    fun buscarTodoContenido(): Flow<List<Contenido>>

    // Buscar contenido por tipo (película o serie)
    @Query("SELECT * FROM contenido WHERE tipo = :tipo")
    fun buscarContenidoPorTipo(tipo: String): Flow<List<Contenido>>

    // Insertar nuevo contenido
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarContenido(contenido: Contenido)

    // Actualizar contenido existente
    @Update
    suspend fun actualizarContenido(contenido: Contenido)

    // Eliminar contenido
    @Delete
    suspend fun eliminarContenido(contenido: Contenido)

    // Buscar contenido por título (búsqueda parcial)
    @Query("SELECT * FROM contenido WHERE titulo LIKE '%' || :titulo || '%'")
    fun buscarContenidoPorTitulo(titulo: String): Flow<List<Contenido>>

    // Buscar contenido por ID
    //@Query("SELECT * FROM contenido WHERE contenido_id = :id")
    //suspend fun buscarContenidoPorId(id: Int): Contenido?

    // Buscar todas las series (tipo = 'serie')
    @Query("SELECT * FROM contenido WHERE tipo = 'serie'")
    fun buscarTodasLasSeries(): Flow<List<Contenido>>

    // Buscar todas las películas (tipo = 'pelicula')
    @Query("SELECT * FROM contenido WHERE tipo = 'pelicula'")
    fun buscarTodasLasPeliculas(): Flow<List<Contenido>>
}
