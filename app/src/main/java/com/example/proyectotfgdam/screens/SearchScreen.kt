package com.example.proyectotfgdam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectotfgdam.ContenidoGrid
import com.example.proyectotfgdam.ModalDetallesPelicula
import com.example.proyectotfgdam.data.Contenido

@Composable
fun SearchScreen() {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    var peliculaSeleccionada by remember { mutableStateOf<Contenido?>(null) } // Estado para la película seleccionada

    // Filtramos las películas según el texto de búsqueda
    val peliculasFiltradas = ContenidoRepository.listaContenido.filter { pelicula ->
        val tituloPelicula = stringResource(id = pelicula.titulo)
        tituloPelicula.contains(query.text, ignoreCase = true) // Comparamos los textos
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer // Fondo para toda la pantalla
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Título de la pantalla
            Text(
                text = "Buscar",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp), // Ajustamos el padding
                textAlign = TextAlign.Center
            )

            // Barra de búsqueda
            SearchBar(query = query, onQueryChange = { query = it })

            // Añadir espacio después de la barra de búsqueda
            Spacer(modifier = Modifier.height(16.dp))

            // El contenido del grid va dentro del LazyColumn, ahora con weight para que ocupe el espacio restante
            LazyColumn(
                modifier = Modifier
                    //.fillMaxWidth() // Ocupa todo el ancho disponible

            ) {
                // Mostrar el grid de películas filtradas
                if (peliculasFiltradas.isNotEmpty()) {
                    item {
                        ContenidoGrid(contenidoList = peliculasFiltradas) { pelicula ->
                            // Al hacer clic, almacenamos la película seleccionada
                            peliculaSeleccionada = pelicula
                        }
                    }
                } else {
                    // Mostrar un mensaje si no hay resultados
                    item {
                        Text(
                            text = "No se encontraron resultados",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Mostrar el modal cuando se haya seleccionado una película
                peliculaSeleccionada?.let { pelicula ->
                    item {
                        ModalDetallesPelicula(
                            contenido = pelicula,
                            onDismiss = { peliculaSeleccionada = null } // Cerrar el modal
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(query: TextFieldValue, onQueryChange: (TextFieldValue) -> Unit) {
    // Barra de búsqueda
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth() // Aseguramos que ocupe todo el ancho
            .padding(8.dp) // Padding vertical ajustado para mayor espacio
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .height(56.dp), // Aumentamos la altura para hacerla más visible
        placeholder = { Text("Buscar películas...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search, // Usamos el ícono de lupa de Material Icons
                contentDescription = "Buscar",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        textStyle = MaterialTheme.typography.bodyLarge
    )
}
