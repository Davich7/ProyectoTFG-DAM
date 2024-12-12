package com.example.proyectotfgdam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    Scaffold(
        topBar = { MyTopBar() }, // Barra superior centralizada
        bottomBar = { MyBotBar(navController) }, // Barra inferior centralizada
        content = { paddingValues ->
            NavHostContainer(navController, Modifier.fillMaxSize().padding(paddingValues))
        }
    )
}

@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen() // Aquí llamamos a la HomeScreen sin TopBar ni BottomBar
        }
        composable("search") {
            SearchScreen()// Agrega la pantalla de búsqueda aquí
        }
        composable("favorites") {
            FavoriteScreen()// Agrega la pantalla de favoritos aquí
        }
    }
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
fun MyBotBar(navController: NavController) {
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
            IconButton(onClick = { navController.navigate("home")  }) {// Navega a la pantalla de inicio
                Icon(
                    imageVector = Icons.Filled.Home, // Puedes cambiar el icono
                    contentDescription = "Inicio",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = { navController.navigate("search") }) {
                Icon(
                    imageVector = Icons.Filled.Search, // Puedes cambiar el icono
                    contentDescription = "Buscar",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = { navController.navigate("favorites") }) {
                Icon(
                    imageVector = Icons.Filled.Favorite, // Puedes cambiar el icono
                    contentDescription = "Favoritos",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

