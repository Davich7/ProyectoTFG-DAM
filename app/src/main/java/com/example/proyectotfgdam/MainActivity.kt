package com.example.proyectotfgdam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.compose.ProyectoTFGDAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoTFGDAMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    PantallaInicio()
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
        _peliculas.value = List(10) { "Película ${it + 1}" }
    }
}

@Composable
fun PantallaInicio() {
    val peliculasViewModel = remember { PeliculasViewModel() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MyTopBar()
        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                BarraDeBusqueda()
                SeccionDestacadas()
                SeccionInicio(titulo = "Añadidas Recientemente", peliculasViewModel)
                Spacer(modifier = Modifier.height(16.dp))
                BarraSeparadora()
                Spacer(modifier = Modifier.height(16.dp))
                SeccionInicio(titulo = "Recomendadas Semanalmente", peliculasViewModel)
                Spacer(modifier = Modifier.height(16.dp))
                BarraSeparadora()
                Spacer(modifier = Modifier.height(16.dp))
                SeccionInicio(titulo = "Mejor Valoración", peliculasViewModel)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun SeccionInicio(titulo: String, peliculasViewModel: PeliculasViewModel) {
    val peliculas = peliculasViewModel.peliculas.value

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(peliculas.size) { index ->
                RectanguloPelis(index, peliculas[index]) {
                    // Acción al hacer click
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
            .size(width = 100.dp, height = 150.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = nombrePelicula,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun SeccionDestacadas() {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Aquí iría el layour de destacadas
        RectanguloDestacado()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun RectanguloDestacado() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Novedoso",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge //
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onPrimaryContainer),
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
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeBusqueda() {
    var searchQuery by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        label = { Text("Buscar...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Buscar"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    )
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LightModePreview() {
    ProyectoTFGDAMTheme(darkTheme = false) {
        PantallaInicio()
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkModePreview() {
    ProyectoTFGDAMTheme(darkTheme = true) {
        PantallaInicio()
    }
}
