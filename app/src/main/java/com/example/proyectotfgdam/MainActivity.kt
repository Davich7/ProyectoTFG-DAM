package com.example.proyectotfgdam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.ProyectoTFGDAMTheme
import com.example.proyectotfgdam.data.Contenido
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

            SearchScreen()
        }
        composable("favorites") {
            FavoriteScreen()
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
                Image(
                    painter = painterResource(id = R.drawable.filmcity_logo), // Reemplaza con el ID de tu recurso de imagen
                    contentDescription = "Logo",
                    modifier = Modifier.size(128.dp) // Tamaño de la imagen
                )
            }
        },
        navigationIcon = {
            // Aquí puedes colocar un ícono de menú si lo necesitas, pero ya está en `actions`
        },
        actions = {
            // Icono para abrir el dropdown
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menú",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            // Menú desplegable con las opciones
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }, // Cierra el menú al hacer clic fuera
            ) {
                val menuText = if (isDarkTheme) "Cambiar a modo claro" else "Cambiar a modo oscuro"

                // Item para cambiar el tema
                DropdownMenuItem(
                    text = {
                        Text(
                            text = menuText,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        onThemeChange() // Cambiar tema
                        isDarkTheme = !isDarkTheme // Cambiar el estado local del tema
                        expanded = false // Cerrar el menú después de hacer la selección
                    },
                    modifier = Modifier.fillMaxWidth() // Hace que el menú ocupe todo el ancho
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





@Composable
fun CartelPelis(
    imageResourceId: Int, // Recibe el ID del recurso de la imagen
    onClick: () -> Unit
) {
    // Definir las dimensiones para el cartel
    val width = 120.dp
    val height = 200.dp

    Card(
        modifier = Modifier
            .width(width) // Ancho fijo para los carteles
            .height(height) // Altura fija para los carteles
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(4.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            // Mostrar la imagen con el ID de recurso proporcionado
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null, // Especifica null ya que no es necesario para imágenes estáticas
                modifier = Modifier
                    .fillMaxSize(), // La imagen llena el tamaño del cartel
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun ContenidoGrid(
    contenidoList: List<Contenido>, // Lista de contenido
    onContenidoClick: (Contenido) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp) // Reducimos el padding general
    ) {
        // Dividir el contenido en filas de 3 elementos
        contenidoList.chunked(3).forEach { contenidoFila ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp), // Añadimos más separación entre las filas
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio horizontal entre las películas
            ) {
                // Mostrar cada película en una fila usando forEach
                contenidoFila.forEach { contenido ->
                    CartelPelis(
                        imageResourceId = contenido.imageResourceId, // Usar la imagen de la película
                        onClick = { onContenidoClick(contenido) } // Acción al hacer clic en el cartel
                    )
                }
            }
        }
    }
}



@Composable
fun ModalDetallesPelicula(
    contenido: Contenido, // El contenido de la película que se pasa al modal
    onDismiss: () -> Unit // Función que se llama al cerrar el modal
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Título de la película
                Text(
                    text = stringResource(id = contenido.titulo), // Usamos el título directamente
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                // Fila con los detalles (cartel y otros detalles)
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Cartel de la película (puedes agregar una imagen aquí si lo deseas)
                    Box(
                        modifier = Modifier
                            .size(100.dp, 150.dp)
                            .background(MaterialTheme.colorScheme.primary)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = painterResource(id = contenido.imageResourceId),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Información de la película
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        // Género
                        Row {
                            Text(
                                text = "Género: ", // Texto estático para el género
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = stringResource(id = contenido.genero), // Usamos el género directamente
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Año
                        Row {
                            Text(
                                text = "Año: ", // Texto estático para el año
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = stringResource(id = contenido.anio), // Usamos el año directamente
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Sinopsis
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Sinopsis: ", // Texto estático para la sinopsis
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(contenido.sinopsis), // Usamos la sinopsis directamente
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Botón para cerrar el modal
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Cerrar") // Texto estático para el botón
                }
            }
        }
    }
}





