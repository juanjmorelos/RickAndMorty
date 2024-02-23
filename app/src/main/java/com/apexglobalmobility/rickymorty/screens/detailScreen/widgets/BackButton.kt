package com.apexglobalmobility.rickymorty.screens.detailScreen.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null
        )
    }
}