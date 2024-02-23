package com.apexglobalmobility.rickymorty

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.apexglobalmobility.rickymorty.screens.homeScreen.HomeScreen
import com.apexglobalmobility.rickymorty.screens.homeScreen.widgets.TitleBar
import com.apexglobalmobility.rickymorty.shared.CustomLoadingScreen
import com.apexglobalmobility.rickymorty.ui.theme.RickAndMortyTheme
import com.apexglobalmobility.rickymorty.viewModel.CharacterViewModel
import com.apexglobalmobility.rickymorty.service.ServicePackageManager
import com.apexglobalmobility.rickymorty.viewModel.CharacterEvent


class MainActivity : ComponentActivity() {

    // ViewModel para la gestión de datos y lógica de la interfaz de usuario relacionada con los personajes.
    private val viewModel: CharacterViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickAndMortyTheme {
                Scaffold(
                    topBar = {
                             TitleBar()
                    },
                    content = { paddingValues ->
                        // Se declaran las variables que se van a utilizar para observar los flujos en el ViewModel y obtener datos actuales y estado de carga.
                        val characters by viewModel.characters.collectAsState()
                        val isLoading by viewModel.isLoading.collectAsState(initial = false)
                        val events = viewModel.events.collectAsState()

                        // Se manejan los eventos del viewmodel en este caso se mostrará un toast y el mensaje dependera de la acción del viewmodel
                        events.value?.let { event ->
                            when (event) {
                                is CharacterEvent.ToastMessage -> {
                                    Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        //Se hace la petición al servicio para traer los personajes
                        LaunchedEffect(Unit) {
                            val service = ServicePackageManager(this@MainActivity)
                            viewModel.fetchCharacters(service)
                        }

                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            //Pantalla principal donde se mostaran los personajes una vez hayan cargado
                            HomeScreen(
                                modifier = Modifier.padding(paddingValues),
                                characters = characters,
                                viewModel = viewModel,
                                context = this@MainActivity
                            )
                            //Si esta cargando mostramos al usuario un progressBar y si no quitamos de la vista el progressBar
                            if (isLoading)
                                CustomLoadingScreen(
                                    text = "Cargando personajes...",
                                    modifier = Modifier.zIndex(1f)
                                )
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMortyTheme {

    }
}