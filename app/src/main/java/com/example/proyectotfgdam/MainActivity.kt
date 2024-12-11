package com.example.proyectotfgdam

import android.util.Log // Asegúrate de tener esta importación

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.compose.ProyectoTFGDAMTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoTFGDAMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // Asegúrate de que el color sea adecuado
                ) {
                    PantallaInicio() // Esto mostrará el contenido con el color de fondo correcto

                }
            }
        }
    }

}

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
fun PantallaInicio() {
    Scaffold(
        topBar = { MyTopBar() },
        bottomBar = { MyBotBar() } // Aquí agregamos la BottomBar
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.primaryContainer // Fondo para toda la pantalla
        ) {
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
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 150.dp) // Tamaño más pequeño para que quepan más
            .background(MaterialTheme.colorScheme.primary)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = nombrePelicula,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2, // Evitar desbordes
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun RectanguloDestacado() {
    val novedades = listOf("Destacado 1", "Destacado 2", "Destacado 3", "Destacado 4", "Destacado 5")
    val totalNovedades = novedades.size

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
            .clip(RoundedCornerShape(8.dp)),
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "FilmaCity",
                color = MaterialTheme.colorScheme.primary
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                println("Menú clickeado")
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menú",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary // Aquí se cambia el color de fondo sin usar background
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBotBar() {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth() // Asegura que ocupe todo el ancho
            .height(56.dp), // Altura estándar para una BottomAppBar
        containerColor = MaterialTheme.colorScheme.onPrimary // Color de fondo de la BottomBar
    ) {
        // Distribuir los iconos de manera uniforme en la barra
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly, // Distribuir de forma uniforme
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Añadir 4 iconos (puedes cambiar los iconos por los que desees)
            IconButton(onClick = { println("Ícono 1 clickeado") }) {
                Icon(
                    imageVector = Icons.Filled.Home, // Puedes cambiar el icono
                    contentDescription = "Inicio",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = { println("Ícono 2 clickeado") }) {
                Icon(
                    imageVector = Icons.Filled.Search, // Puedes cambiar el icono
                    contentDescription = "Buscar",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = { println("Ícono 3 clickeado") }) {
                Icon(
                    imageVector = Icons.Filled.Favorite, // Puedes cambiar el icono
                    contentDescription = "Favoritos",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}





//A PARTIR DE AQUI VAN LAS FUNCIONES DE PREVISUALIZACION

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LightModePreview() {
    ProyectoTFGDAMTheme(darkTheme = false) {
        PantallaInicio()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    ProyectoTFGDAMTheme(darkTheme = true) {
        PantallaInicio()
    }
}
