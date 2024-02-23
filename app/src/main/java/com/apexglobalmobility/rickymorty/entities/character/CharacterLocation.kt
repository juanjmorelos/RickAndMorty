package com.apexglobalmobility.rickymorty.entities.character

import java.io.Serializable

data class CharacterLocation (
    val name: String,
    val url: String
) : Serializable