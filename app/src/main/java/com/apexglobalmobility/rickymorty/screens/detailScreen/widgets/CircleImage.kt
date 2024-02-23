package com.apexglobalmobility.rickymorty.screens.detailScreen.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apexglobalmobility.rickymorty.screens.shared.ImageNetwork

@Composable
fun CircleImage(image: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 8.dp),
        contentAlignment = Alignment.Center
    ) {
        ImageNetwork(
            url = image,
            modifier = Modifier
                .width(170.dp)
                .height(170.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = CircleShape
                )
        )
    }
}