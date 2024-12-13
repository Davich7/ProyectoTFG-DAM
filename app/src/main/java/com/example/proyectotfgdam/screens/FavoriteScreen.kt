package com.example.proyectotfgdam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectotfgdam.ContenidoGrid
import com.example.proyectotfgdam.ModalDetallesPelicula
import com.example.proyectotfgdam.data.Contenido

@Composable
fun FavoriteScreen() {
    val (showModal, setShowModal) = remember { mutableStateOf(false) }
    val (selectedPelicula, setSelectedPelicula) = remember { mutableStateOf<Contenido?>(null) }

    fun onPeliculaClick(pelicula: Contenido) {
        setSelectedPelicula(pelicula)
        setShowModal(true)
    }

    val peliculasFavoritas = getPeliculasFavoritas()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        contentPadding = PaddingValues(16.dp) // Añadimos un padding alrededor de la columna
    ) {
        item {
            Text(
                text = "Favoritos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            ContenidoGrid(
                contenidoList = peliculasFavoritas, // Pasamos solo las películas favoritas
                onContenidoClick = { pelicula ->
                    onPeliculaClick(pelicula) // Maneja el clic en una película
                }
            )
        }

        if (showModal && selectedPelicula != null) {
            item {
                ModalDetallesPelicula(
                    contenido = selectedPelicula!!, // Aseguramos que no sea nulo
                    onDismiss = { setShowModal(false) }
                )
            }
        }
    }
}

fun getPeliculasFavoritas(): List<Contenido> {
    return ContenidoRepository.listaContenido.filter { it.esFavorito }
}
