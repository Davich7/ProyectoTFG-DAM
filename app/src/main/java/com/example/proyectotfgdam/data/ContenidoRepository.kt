import com.example.proyectotfgdam.data.Contenido
import com.example.proyectotfgdam.R

class ContenidoRepository {
    companion object {
        // Lista de objetos Contenido con el nuevo campo esFavorito
        val listaContenido = listOf(
            Contenido(
                id = 1,
                imageResourceId = R.drawable.image_el_padrino,
                titulo = R.string.El_Padrino_titulo,
                anio = R.string.El_Padrino_anio,
                genero = R.string.El_Padrino_genero,
                sinopsis = R.string.El_Padrino_genero,
                temporadas = R.string.El_Padrino_tempradas,
                esFavorito = true,// valor por defecto
            ),
            Contenido(
                id = 2,
                imageResourceId = R.drawable.image_batman,
                titulo = R.string.El_Caballero_Oscuro_titulo,
                anio = R.string.El_Caballero_Oscuro_anio,
                genero = R.string.El_Caballero_Oscuro_genero,
                sinopsis = R.string.El_Caballero_Oscuro_sinopsis,
                temporadas = R.string.El_Caballero_Oscuro_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 3,
                imageResourceId = R.drawable.image_pulp_fiction,
                titulo = R.string.Pulp_Fiction_titulo,
                anio = R.string.Pulp_Fiction_anio,
                genero = R.string.Pulp_Fiction_genero,
                sinopsis = R.string.Pulp_Fiction_sinopsis,
                temporadas = 0,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 4,
                imageResourceId = R.drawable.image_senior_anillos,
                titulo = R.string.El_Senor_de_los_Anillos,
                anio = R.string.El_Senor_de_los_Anillos_anio,
                genero = R.string.El_Senor_de_los_Anillos_genero,
                sinopsis = R.string.El_Senor_de_los_Anillos_sinopsis,
                temporadas = R.string.El_Senor_de_los_Anillos_temproadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 5,
                imageResourceId = R.drawable.image_forrest_gump,
                titulo = R.string.Forrest_Gump_titulo,
                anio = R.string.Forrest_Gump_anio,
                genero = R.string.Forrest_Gump_genero,
                sinopsis = R.string.Forrest_Gump_sinopsis,
                temporadas = R.string.Forrest_Gump_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 6,
                imageResourceId = R.drawable.image_matrix,
                titulo = R.string.Matrix_titulo,
                anio = R.string.Matrix_anio,
                genero = R.string.Matrix_genero,
                sinopsis = R.string.Matrix_sinopsis,
                temporadas = R.string.Matrix_temporadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 7,
                imageResourceId = R.drawable.image_gladiator,
                titulo = R.string.Gladiador_titulo,
                anio = R.string.Gladiador_anio,
                genero = R.string.Gladiador_genero,
                sinopsis = R.string.Gladiador_sinopsis,
                temporadas = R.string.Gladiador_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 8,
                imageResourceId = R.drawable.image_titanic,
                titulo = R.string.Titanic_titulo,
                anio = R.string.Titanic_anio,
                genero = R.string.Titanic_genero,
                sinopsis = R.string.Titanic_sinopsis,
                temporadas = R.string.Titanic_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 9,
                imageResourceId = R.drawable.image_el_silencio_de,
                titulo = R.string.El_Silencio_de_los_Inocentes_titulo,
                anio = R.string.El_Silencio_de_los_Inocentes_anio,
                genero = R.string.El_Silencio_de_los_Inocentes_genero,
                sinopsis = R.string.El_Silencio_de_los_Inocentes_sinopsis,
                temporadas = R.string.El_Silencio_de_los_Inocentes_temporadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 10,
                imageResourceId = R.drawable.image_avengers,
                titulo = R.string.Los_Vengadores_titulo,
                anio = R.string.Los_Vengadores_anio,
                genero = R.string.Los_Vengadores_genero,
                sinopsis = R.string.Los_Vengadores_sinopsis,
                temporadas = R.string.Los_Vengadores_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 11,
                imageResourceId = R.drawable.image_toy_story,
                titulo = R.string.Toy_Story_titulo,
                anio = R.string.Toy_Story_anio,
                genero = R.string.Toy_Story_genero,
                sinopsis = R.string.Toy_Story_sinopsis,
                temporadas = R.string.Toy_Story_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 12,
                imageResourceId = R.drawable.image_coco,
                titulo = R.string.Coco_titulo,
                anio = R.string.Coco_anio,
                genero = R.string.Coco_genero,
                sinopsis = R.string.Coco_sinopsis,
                temporadas = R.string.Coco_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 13,
                imageResourceId = R.drawable.image_la_vida_es_bella,
                titulo = R.string.La_Vida_es_Bella_titulo,
                anio = R.string.La_Vida_es_Bella_anio,
                genero = R.string.La_Vida_es_Bella_genero,
                sinopsis = R.string.La_Vida_es_Bella_sinopsis,
                temporadas = R.string.La_Vida_es_Bella_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 14,
                imageResourceId = R.drawable.image_shindler_list,
                titulo = R.string.La_Lista_de_Schindler_titulo,
                anio = R.string.La_Lista_de_Schindler_anio,
                genero = R.string.La_Lista_de_Schindler_genero,
                sinopsis = R.string.La_Lista_de_Schindler_sinopsis,
                temporadas = R.string.La_Lista_de_Schindler_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 15,
                imageResourceId = R.drawable.image_star_wars,
                titulo = R.string.Star_Wars_Una_Nueva_Esperanza_titulo,
                anio = R.string.Star_Wars_Una_Nueva_Esperanza_anio,
                genero = R.string.Star_Wars_Una_Nueva_Esperanza_genero,
                sinopsis = R.string.Star_Wars_Una_Nueva_Esperanza_sinopsis,
                temporadas = R.string.Star_Wars_Una_Nueva_Esperanza_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 16,
                imageResourceId = R.drawable.image_jurassic,
                titulo = R.string.Jurassic_Park_titulo,
                anio = R.string.Jurassic_Park_anio,
                genero = R.string.Jurassic_Park_genero,
                sinopsis = R.string.Jurassic_Park_sinopsis,
                temporadas = R.string.Jurassic_Park_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 17,
                imageResourceId = R.drawable.image_avatar,
                titulo = R.string.Avatar_titulo,
                anio = R.string.Avatar_anio,
                genero = R.string.Avatar_genero,
                sinopsis = R.string.Avatar_sinopsis,
                temporadas = R.string.Avatar_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 18,
                imageResourceId = R.drawable.image_los_increibles,
                titulo = R.string.Los_Increibles_titulo,
                anio = R.string.Los_Increibles_anio,
                genero = R.string.Los_Increibles_genero,
                sinopsis = R.string.Los_Increibles_sinopsis,
                temporadas = R.string.Los_Increibles_temporadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 19,
                imageResourceId = R.drawable.image_up,
                titulo = R.string.Up_titulo,
                anio = R.string.Up_anio,
                genero = R.string.Up_genero,
                sinopsis = R.string.Up_sinopsis,
                temporadas = R.string.Up_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 20,
                imageResourceId = R.drawable.image_shrek,
                titulo = R.string.Shrek_titulo,
                anio = R.string.Shrek_anio,
                genero = R.string.Shrek_genero,
                sinopsis = R.string.Shrek_sinopsis,
                temporadas = R.string.Shrek_temporadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 21,
                imageResourceId = R.drawable.image_harry_potter,
                titulo = R.string.Harry_Potter_y_la_Piedra_Filosofal_titulo,
                anio = R.string.Harry_Potter_y_la_Piedra_Filosofal_anio,
                genero = R.string.Harry_Potter_y_la_Piedra_Filosofal_genero,
                sinopsis = R.string.Harry_Potter_y_la_Piedra_Filosofal_sinopsis,
                temporadas = R.string.Harry_Potter_y_la_Piedra_Filosofal_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 22,
                imageResourceId = R.drawable.image_breaking_bad,
                titulo = R.string.Breaking_Bad_titulo,
                anio = R.string.Breaking_Bad_anio,
                genero = R.string.Breaking_Bad_genero,
                sinopsis = R.string.Breaking_Bad_sinopsis,
                temporadas = R.string.Breaking_Bad_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 23,
                imageResourceId = R.drawable.image_game_of_thrones,
                titulo = R.string.Game_of_Thrones_titulo,
                anio = R.string.Game_of_Thrones_anio,
                genero = R.string.Game_of_Thrones_genero,
                sinopsis = R.string.Game_of_Thrones_sinopsis,
                temporadas = R.string.Game_of_Thrones_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 24,
                imageResourceId = R.drawable.image_stranger_things,
                titulo = R.string.Stranger_Things_titulo,
                anio = R.string.Stranger_Things_anio,
                genero = R.string.Stranger_Things_genero,
                sinopsis = R.string.Stranger_Things_sinopsis,
                temporadas = R.string.Stranger_Things_temporadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 25,
                imageResourceId = R.drawable.image_the_walking_dead,
                titulo = R.string.The_Walking_Dead_titulo,
                anio = R.string.The_Walking_Dead_anio,
                genero = R.string.The_Walking_Dead_genero,
                sinopsis = R.string.The_Walking_Dead_sinopsis,
                temporadas = R.string.The_Walking_Dead_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 26,
                imageResourceId = R.drawable.image_the_crown,
                titulo = R.string.The_Crown_titulo,
                anio = R.string.The_Crown_anio,
                genero = R.string.The_Crown_genero,
                sinopsis = R.string.The_Crown_sinopsis,
                temporadas = R.string.The_Crown_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 27,
                imageResourceId = R.drawable.image_friends,
                titulo = R.string.Friends_titulo,
                anio = R.string.Friends_anio,
                genero = R.string.Friends_genero,
                sinopsis = R.string.Friends_sinopsis,
                temporadas = R.string.Friends_temporadas,
                esFavorito = false // valor por defecto
            ),
            Contenido(
                id = 28,
                imageResourceId = R.drawable.image_the_office,
                titulo = R.string.The_Office_titulo,
                anio = R.string.The_Office_anio,
                genero = R.string.The_Office_genero,
                sinopsis = R.string.The_Office_sinopsis,
                temporadas = R.string.The_Office_temporadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 29,
                imageResourceId = R.drawable.image_sherlock,
                titulo = R.string.Sherlock_titulo,
                anio = R.string.Sherlock_anio,
                genero = R.string.Sherlock_genero,
                sinopsis = R.string.Sherlock_sinopsis,
                temporadas = R.string.Sherlock_temporadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 30,
                imageResourceId = R.drawable.image_country_rain,
                titulo = R.string.Country_Rain_titulo,
                anio = R.string.Country_Rain_anio,
                genero = R.string.Country_Rain_genero,
                sinopsis = R.string.Country_Rain_sinopsis,
                temporadas = R.string.Country_Rain_temporadas,
                esFavorito = true // valor por defecto
            ),
            Contenido(
                id = 31,
                imageResourceId = R.drawable.image_peaky_blinders,
                titulo = R.string.Peaky_Blinders_titulo,
                anio = R.string.Peaky_Blinders_anio,
                genero = R.string.Peaky_Blinders_genero,
                sinopsis = R.string.Peaky_Blinders_sinopsis,
                temporadas = R.string.Peaky_Blinders_temporadas,
                esFavorito = false // valor por defecto
            ),
        )
    }
}

