package com.apexglobalmobility.rickymorty.entities.character

import java.io.Serializable

//Modelo de la clase del personaje
data class Character (
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocation,
    val location: CharacterLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) : Serializable
