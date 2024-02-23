package com.apexglobalmobility.rickymorty.screens.homeScreen.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.apexglobalmobility.rickymorty.entities.character.Character

@OptIn(ExperimentalUnitApi::class)
@Composable
fun CardDetail(modifier: Modifier = Modifier, character: Character) {
    Column() {
        Text(
            text = character.name,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            modifier = modifier.padding(16.dp, 8.dp, 16.dp, 0.dp)
        )
        Text(
            text = character.species,
            color = Color.White,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            modifier = modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
        )
    }
}