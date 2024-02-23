package com.apexglobalmobility.rickymorty.screens.homeScreen.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apexglobalmobility.rickymorty.entities.character.Character
import com.apexglobalmobility.rickymorty.screens.shared.AliveWidget
import com.apexglobalmobility.rickymorty.screens.shared.ImageNetwork

@Composable
fun CharacterCard( modifier: Modifier = Modifier, character: Character, onClick: () -> Unit ) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.scrim,
        )
    ) {
        Box() {
            Column {
                ImageNetwork(character.image, modifier.fillMaxWidth().height(150.dp))
                CardDetail(character = character)
            }
            AliveWidget(
                character = character,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }
    }
}