package com.apexglobalmobility.rickymorty.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomLoadingScreen(modifier: Modifier = Modifier, text: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background( color = Color.Black.copy(alpha = 0.7f))
            .wrapContentSize(align = Alignment.Center)
        ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CircularProgressIndicator(color = Color.White)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text, modifier = Modifier.padding(0.dp, 16.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }

    }

}
