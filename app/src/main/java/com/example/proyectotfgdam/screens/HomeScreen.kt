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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.ProyectoTFGDAMTheme
import kotlinx.coroutines.delay

// ViewModel simulado para cargar películas
class PeliculasViewModel : ViewModel() {
    private val _peliculas = mutableStateOf<List<String>>(emptyList())
    val peliculas: State<List<String>> = _peliculas

    init {
        // Cargar las películas simuladas (esto lo puedes sustituir por un API real)
        _peliculas.value = List(20) { "Película ${it + 1}" }
    }
}

// Pantalla principal con Scaffold
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer // Fondo para toda la pantalla
    ) {
        InicialSecciones() // Llamada a las secciones
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
        contentPadding = PaddingValues(vertical = 16.dp) // Espacio extra para asegurar scroll
    ) {

        item { RectanguloDestacado() }
        item {
            SeccionInicio(
                titulo = "Añadidas Recientemente",
                peliculasViewModel = peliculasViewModel
            )
        }
        item { BarraSeparadora() }
        item {
            SeccionInicio(
                titulo = "Recomendadas Semanalmente",
                peliculasViewModel = peliculasViewModel
            )
        }
        item { BarraSeparadora() }
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
        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(peliculas.size) { index ->
                RectanguloPelis(index, peliculas[index]) {
                    println("Película clickeada: ${peliculas[index]}")
                }
            }
        }
    }
}









@Composable
fun RectanguloPelis(index: Int, nombrePelicula: String, onClick: () -> Unit) {
    var mostrarModal by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 150.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clip(RoundedCornerShape(8.dp))
            .clickable { mostrarModal = true },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = nombrePelicula,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }

    if (mostrarModal) {
        ModalDetallesPelicula(
            pelicula = nombrePelicula, // Cambiar a un modelo con más detalles en el futuro
            onDismiss = { mostrarModal = false }
        )
    }
}


@Composable
fun RectanguloDestacado() {
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
                    selectedColor = MaterialTheme.colorScheme.secondary
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





@Composable
fun ModalDetallesPelicula(
    pelicula: String, // Cambiar a tu modelo de datos (por ejemplo: Pelicula)
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Añadimos espacio entre los elementos
            ) {
                // Título de la película
                Text(
                    //RECIBIR NOMBRE DE LA PELICULA DESDE LA BBDD
                    text = "El hombre de acero",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(), // Hace que ocupe todo el ancho
                    textAlign = TextAlign.Center
                )

                // Row con el cartel y los detalles
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Cartel de la película (usamos un Box simulado por ahora)
                    Box(
                        modifier = Modifier
                            .size(100.dp, 150.dp)
                            .background(MaterialTheme.colorScheme.primary)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        // Aquí puedes poner una imagen del cartel
                    }

                    // Información de la película (Dirección y Actores)
                    Column(
                        modifier = Modifier.weight(1f) // Hace que esta columna ocupe el espacio restante
                    ) {
                        // Tipo de Contenido
                        Row {
                            Text(
                                text = "Tipo: ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Película",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        // Fecha
                        Row {
                            Text(
                                text = "Año: ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "2013",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        // Dirección
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Dirección",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Zack Snyder",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Actores
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Reparto Principal",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Henry Cavill",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Sinopsis
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Sinopsis",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Sinopsis de la película, aquí iría la sinopsis de la película.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Botón para cerrar el modal
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
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