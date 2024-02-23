package com.apexglobalmobility.rickymorty.screens.homeScreen.widgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.apexglobalmobility.rickymorty.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(modifier: Modifier = Modifier) {
    Surface() {
        TopAppBar(
            title = {
                Text(text = "Rick and Morty")
            }
        )
    }
}