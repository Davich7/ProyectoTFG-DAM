package com.example.proyectotfgdam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.ProyectoTFGDAMTheme
import com.example.proyectotfgdam.screens.FavoriteScreen
import com.example.proyectotfgdam.screens.HomeScreen
import com.example.proyectotfgdam.screens.SearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoTFGDAMTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var isDarkTheme by remember { mutableStateOf(false) } // Estado para controlar el tema
    // La aplicación usa el tema dependiendo de la variable isDarkTheme
    ProyectoTFGDAMTheme(darkTheme = isDarkTheme) {
        Scaffold(
            topBar = {
                MyTopBar(onThemeChange = { isDarkTheme = !isDarkTheme }) // Pasamos la función de cambio de tema
            },
            bottomBar = {
                MyBotBar(navController = navController) // Aquí usas MyBotBar para la barra inferior
            },
            content = { paddingValues ->
                NavHostContainer(
                    navController,
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues) // Para que no se superponga con la barra de navegación
                )
            }
        )
    }
}


@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen()
        }
        composable("search") {
            val dummyPeliculas = List(30) { "Película ${it + 1}" } // Lista simulada

            SearchScreen(
                peliculas = dummyPeliculas,
                onPeliculaClick = { pelicula ->
                    println("Seleccionaste la película: $pelicula")
                    // Navegar a los detalles si es necesario
                }
            )
        }
        composable("favorites") {
            val dummyFavoritas = List(10) { "Favorita ${it + 1}" } // Lista simulada

            FavoriteScreen(
                peliculas = dummyFavoritas,
                onPeliculaClick = { pelicula ->
                    println("Seleccionaste la favorita: $pelicula")
                }
            )
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(onThemeChange: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var isDarkTheme by remember { mutableStateOf(false) } // Variable para almacenar el estado del tema

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.foundation.Image(
                    painter = painterResource(id = R.drawable.filmcity_logo), // Reemplaza con el ID de tu recurso de imagen
                    contentDescription = "Logo",
                    modifier = Modifier.size(128.dp) // Tamaño de la imagen
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menú",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            // El menú desplegable con las opciones
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                val menuText = if (isDarkTheme) "Cambiar a modo claro" else "Cambiar a modo oscuro"

                DropdownMenuItem(
                    text = {
                        Text(
                            menuText,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        onThemeChange() // Cambiar tema
                        isDarkTheme = !isDarkTheme // Cambiar el estado local de tema
                        expanded = false // Cerrar el menú después de hacer la selección
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)

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
fun MyBotBar(navController: NavController) {
    // Definir el estado para el índice seleccionado
    var selectedIndex by remember { mutableStateOf(0) }

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), // Altura estándar para una BottomAppBar
        containerColor = MaterialTheme.colorScheme.onPrimary // Color de fondo de la BottomBar
    ) {
        // Distribuir los iconos de manera uniforme en la barra
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly, // Distribuir de forma uniforme
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono de "Inicio"
            IconButton(onClick = {
                navController.navigate("home")
                selectedIndex = 0 // Cambiar el índice de la sección activa
            }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Inicio",
                    tint = if (selectedIndex == 0) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                )
            }

            // Icono de "Buscar"
            IconButton(onClick = {
                navController.navigate("search")
                selectedIndex = 1 // Cambiar el índice de la sección activa
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar",
                    tint = if (selectedIndex == 1) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                )
            }

            // Icono de "Favoritos"
            IconButton(onClick = {
                navController.navigate("favorites")
                selectedIndex = 2 // Cambiar el índice de la sección activa
            }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favoritos",
                    tint = if (selectedIndex == 2) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                )
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

@Composable
fun CartelPelis(index: Int, nombrePelicula: String, onClick: () -> Unit) {
    var mostrarModal by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .aspectRatio(2f / 3f) // Relación de aspecto ancho:alto (por ejemplo, 2:3)
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
            pelicula = nombrePelicula,
            onDismiss = { mostrarModal = false }
        )
    }
}


@Composable
fun GridPeliculas(
    peliculas: List<String>,
    onPeliculaClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Tres columnas en el grid
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(peliculas.size) { index ->
            CartelPelis(index = index, nombrePelicula = peliculas[index]) {
                onPeliculaClick(peliculas[index])
            }
        }
    }
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
