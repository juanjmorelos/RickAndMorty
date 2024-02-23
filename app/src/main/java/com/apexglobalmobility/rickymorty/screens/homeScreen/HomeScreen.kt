package com.apexglobalmobility.rickymorty.screens.homeScreen

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apexglobalmobility.rickymorty.entities.character.Character
import com.apexglobalmobility.rickymorty.screens.detailScreen.DetailActivity
import com.apexglobalmobility.rickymorty.screens.homeScreen.widgets.CharacterCard
import com.apexglobalmobility.rickymorty.viewModel.CharacterViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, characters: List<Character>, viewModel: CharacterViewModel, context: Context) {
    Surface(
        color = Color.Transparent,
    ) {
        //Se utiliza un staggeredGridLayout para que cada item conserve su propia altura, además de brindar un diseño más novedoso
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                //Se cargan los items en el staggeredGrid con el diseño de la card para cada personaje
                items(characters.size) { index ->
                    val character = characters[index]

                    CharacterCard(
                        character = character,
                        onClick = {
                            /*
                            Escuchamos cuando el usuario da clic y lo enviamos la pantall de detalles enviandole por intent
                            el personaje al cual vamos a detallar ahorrandonos una petición al servicio
                            */
                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra("character", character)
                            context.startActivity(intent)
                        }
                    )

                    /*
                    Si estamos llegando al final del scroll se cargaran más personajes, de esta manera hacemos un scroll
                    infinito cargando los personajes páginados
                    */
                    if (index == characters.size - 1) {
                        Log.e("Llegando al final", "Cargando más datos de la página ${viewModel.page}")
                        LaunchedEffect(Unit) {
                            viewModel.loadNextPage()
                        }
                    }
                }
            },
            contentPadding = PaddingValues(8.dp, 4.dp, 8.dp, 4.dp),
            modifier = modifier.clipToBounds()
        )
    }
}

@Preview
@Composable
fun MiNuevaPantallaPreview() {
}