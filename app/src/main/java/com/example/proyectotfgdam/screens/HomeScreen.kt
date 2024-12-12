package com.example.proyectotfgdam.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.example.compose.ProyectoTFGDAMTheme
import com.example.proyectotfgdam.CartelPelis
import com.example.proyectotfgdam.ModalDetallesPelicula
import com.example.proyectotfgdam.PeliculasViewModel
import kotlinx.coroutines.delay


// Pantalla principal con Scaffold
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer // Fondo para toda la pantalla
    ) {
        Column(){
            Text(
                text = "Inicio",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                        ,
                textAlign = TextAlign.Center

            )
            InicialSecciones() // Llamada a las secciones

        }

    }
}


// Secciones iniciales en LazyColumn
@Composable
fun InicialSecciones() {
    val peliculasViewModel = remember { PeliculasViewModel() }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 2.dp) // Espacio extra para asegurar scroll
    ) {

        item { Destacados() }
        item {
            SeccionInicio(
                titulo = "Añadidas Recientemente",
                peliculasViewModel = peliculasViewModel
            )
        }

        item {
            SeccionInicio(
                titulo = "Recomendadas Semanalmente",
                peliculasViewModel = peliculasViewModel
            )
        }
        item {
            SeccionInicio(
                titulo = "Mejor Valoración",
                peliculasViewModel = peliculasViewModel
            )
        }
    }
}



// Vista de películas en LazyRow
@Composable
fun SeccionInicio(titulo: String, peliculasViewModel: PeliculasViewModel) {
    val peliculas = peliculasViewModel.peliculas.value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp) // Añadir padding horizontal para que no quede pegado
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier.height(200.dp) // Ajusta para que los carteles mantengan su proporción
        ) {
            items(peliculas.size) { index ->
                CartelPelis(index, peliculas[index]) {
                    println("Película clickeada: ${peliculas[index]}")
                }
            }
        }
        BarraSeparadora()
    }
}

@Composable
fun Destacados() {
    val novedades = listOf("Destacado 1", "Destacado 2", "Destacado 3", "Destacado 4", "Destacado 5")
    val totalNovedades = novedades.size

    var mostrarModal by remember { mutableStateOf(false) }

    var novedadActual by remember { mutableStateOf(0) }


    // Lanzar una corrutina para cambiar de novedad automáticamente cada 5 segundos
    LaunchedEffect(novedadActual) {
        delay(5000) // 5 segundos
        novedadActual = (novedadActual + 1) % totalNovedades // Cambiar a la siguiente novedad
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clip(RoundedCornerShape(8.dp))
            .clickable { mostrarModal = true },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Mostrar el nombre de la novedad actual
        Text(
            text = novedades[novedadActual],
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Indicadores (puntos) para cada novedad
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaciado de 8dp entre puntos
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally) // Centrado horizontal
        ) {
            novedades.forEachIndexed { index, _ ->
                PuntoIndicador(
                    isSelected = index == novedadActual,
                    color = MaterialTheme.colorScheme.onPrimary,
                    selectedColor = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }

    if (mostrarModal) {
        ModalDetallesPelicula(
            pelicula = novedades.toString(), // Cambiar a un modelo con más detalles en el futuro
            onDismiss = { mostrarModal = false }
        )
    }
}

@Composable
fun PuntoIndicador(
    isSelected: Boolean,
    color: Color,
    selectedColor: Color
) {
    val indicadorColor by animateColorAsState(
        targetValue = if (isSelected) selectedColor else color
    )

    Box(
        modifier = Modifier
            .size(8.dp) // Tamaño del indicador (aumentado a 12.dp)
            .clip(CircleShape) // Asegura que el indicador sea un círculo
            .background(indicadorColor) // Fondo del indicador
            .padding(horizontal = 4.dp) // Espaciado horizontal entre los puntos
    )
}



@Composable
fun BarraSeparadora() {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 16.dp),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onSurface
    )
}

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
}