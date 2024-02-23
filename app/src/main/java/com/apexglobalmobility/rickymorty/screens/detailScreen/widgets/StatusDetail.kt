package com.apexglobalmobility.rickymorty.screens.detailScreen.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.apexglobalmobility.rickymorty.entities.character.Character
import com.apexglobalmobility.rickymorty.screens.shared.AliveWidget
import com.apexglobalmobility.rickymorty.shared.capitalizeIfNot

@OptIn(ExperimentalUnitApi::class)
@Composable
fun StatusDetail(character: Character) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AliveWidget(character = character)
        Text(
            text = character.status.capitalizeIfNot(),
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}