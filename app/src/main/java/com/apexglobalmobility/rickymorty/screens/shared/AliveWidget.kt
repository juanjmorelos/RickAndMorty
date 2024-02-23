package com.apexglobalmobility.rickymorty.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.apexglobalmobility.rickymorty.entities.character.Character

@Composable
fun AliveWidget(modifier: Modifier = Modifier, character: Character) {
    Box(
        modifier = modifier
            .size(20.dp)
            .background(color = statusColor(character), shape = CircleShape)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = CircleShape
            )
    )
}


fun statusColor(character: Character): Color {
    if (character.status.toLowerCase(Locale.current) == "alive") {
        return Color.Green
    } else if (character.status.toLowerCase(Locale.current) == "dead") {
        return Color.Red
    }
    return Color.Gray
}