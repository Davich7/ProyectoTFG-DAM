package com.example.proyectotfgdam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectotfgdam.CartelPelis
import com.example.proyectotfgdam.data.Contenido

@Composable
fun HomeScreen() {
    // Filtramos las películas por género
    val generoDrama = ContenidoRepository.listaContenido.filter { stringResource(id = it.genero) == "Drama" }
    val generoCienciaFiccion = ContenidoRepository.listaContenido.filter { stringResource(id = it.genero) == "Ciencia Ficción" }
    val generoAccion = ContenidoRepository.listaContenido.filter { stringResource(id = it.genero) == "Acción" }
    val generoAnimacion = ContenidoRepository.listaContenido.filter { stringResource(id = it.genero) == "Animación" }

    // Título de la pantalla
    Column {
        Text(
            text = "Inicio",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(top = 16.dp, bottom = 8.dp), // Ajustamos el padding
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            // Sección para películas de Drama
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Drama",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
                if (generoDrama.isNotEmpty()) {
                    LazyRowPeliculas(peliculas = generoDrama)
                } else {
                    Text("No hay películas de Drama")
                }

                Spacer(modifier = Modifier.height(8.dp))
                BarraSeparadora()


            }

            // Sección para películas de Ciencia Ficción
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ciencia Ficción",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
                if (generoCienciaFiccion.isNotEmpty()) {
                    LazyRowPeliculas(peliculas = generoCienciaFiccion)
                } else {
                    Text("No hay películas de Ciencia Ficción")
                }
                Spacer(modifier = Modifier.height(8.dp))
                BarraSeparadora()

            }

            // Sección para películas de Ciencia Ficción
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Acción",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
                if (generoCienciaFiccion.isNotEmpty()) {
                    LazyRowPeliculas(peliculas = generoAccion)
                } else {
                    Text("No hay películas de Acción")
                }
                Spacer(modifier = Modifier.height(8.dp))
                BarraSeparadora()

            }
            // Sección para películas de Animación
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Animación",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
                if (generoCienciaFiccion.isNotEmpty()) {
                    LazyRowPeliculas(peliculas = generoAnimacion)
                } else {
                    Text("No hay películas de Animación")
                }
                Spacer(modifier = Modifier.height(8.dp))
                BarraSeparadora()

            }


        }
    }
}
@Composable
fun LazyRowPeliculas(peliculas: List<Contenido>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Usamos forEach para iterar sobre la lista, pero esto no optimiza el rendimiento
        peliculas.forEach { pelicula ->
            item { // Aquí se debe usar 'item' para cada elemento individual
                CartelPelis(
                    imageResourceId = pelicula.imageResourceId,
                    onClick = {
                        println("Película seleccionada: ${pelicula.titulo}")
                    }
                )
            }
        }
    }
}

@Composable
fun BarraSeparadora() {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 16.dp),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onSurface
    )
}
/*
//A PARTIR DE AQUI VAN LAS FUNCIONES DE PREVISUALIZACION
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LightModePreview() {
    ProyectoTFGDAMTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    ProyectoTFGDAMTheme(darkTheme = true) {
        HomeScreen()
    }
}*/