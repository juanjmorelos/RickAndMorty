@file:OptIn(ExperimentalUnitApi::class)

package com.apexglobalmobility.rickymorty.screens.detailScreen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun DetailTextOption(title: String, detail: String, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column() {
            Text(
                text = title,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 4.dp)
            )
            TextContainer(text = detail)
        }
    }
}

@Composable
fun TextContainer(text: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFE7EBF6), shape = RoundedCornerShape(30.dp))
            .padding(16.dp, 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            color = Color.Black,
        )
    }
}