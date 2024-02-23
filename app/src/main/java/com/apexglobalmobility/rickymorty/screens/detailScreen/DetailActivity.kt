package com.apexglobalmobility.rickymorty.screens.detailScreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.apexglobalmobility.rickymorty.entities.character.Character
import com.apexglobalmobility.rickymorty.screens.detailScreen.widgets.BackButton
import com.apexglobalmobility.rickymorty.screens.detailScreen.widgets.CircleImage
import com.apexglobalmobility.rickymorty.screens.detailScreen.widgets.DetailTextOption
import com.apexglobalmobility.rickymorty.screens.detailScreen.widgets.StatusDetail
import com.apexglobalmobility.rickymorty.screens.shared.AliveWidget
import com.apexglobalmobility.rickymorty.screens.shared.ImageNetwork
import com.apexglobalmobility.rickymorty.shared.capitalizeIfNot
import com.apexglobalmobility.rickymorty.shared.getSerializable
import com.apexglobalmobility.rickymorty.shared.parseToUserFriendlyDate
import com.apexglobalmobility.rickymorty.ui.theme.RickAndMortyTheme
import java.io.Serializable

class DetailActivity : ComponentActivity() {
    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Se obtiene el objeto enviado por el intent desde la lista de personajes para mostrar el detalle del
        //personaje seleccionado
        val receivedIntent: Intent = intent
        character = receivedIntent.getSerializable("character", Character::class.java)

        setContent {
            RickAndMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box {
                        Column(
                            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
                        ) {
                            BackButton { onBackPressedDispatcher.onBackPressed() }
                            CircleImage(character.image)
                            StatusDetail(character = character)
                            DetailTextOption(
                                title = "Name:",
                                detail = character.name,
                                modifier = Modifier
                                    .padding(16.dp, 8.dp)
                                    .fillMaxWidth()
                            )
                            DetailTextOption(
                                title = "Specie:",
                                detail = character.species.capitalizeIfNot(),
                                modifier = Modifier
                                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                                    .fillMaxWidth()
                            )
                            DetailTextOption(
                                title = "Gender:",
                                detail = character.gender.capitalizeIfNot(),
                                modifier = Modifier
                                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                                    .fillMaxWidth()
                            )
                            DetailTextOption(
                                title = "Created date:",
                                detail = character.created.parseToUserFriendlyDate(),
                                modifier = Modifier
                                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                                    .fillMaxWidth()
                            )
                            DetailTextOption(
                                title = "Planet origin:",
                                detail = character.origin.name.capitalizeIfNot(),
                                modifier = Modifier
                                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                                    .fillMaxWidth()
                            )
                            DetailTextOption(
                                title = "Last known location:",
                                detail = character.location.name.capitalizeIfNot(),
                                modifier = Modifier
                                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    RickAndMortyTheme {
    }
}