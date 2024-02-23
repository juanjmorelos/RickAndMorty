package com.apexglobalmobility.rickymorty.shared

import android.content.Intent
import android.os.Build
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

// Se crea una 'extension function' de la clase String para parsear la fecha a una más legible para el usuario
fun String.parseToUserFriendlyDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMMM dd 'of' yyyy", Locale.ENGLISH)

    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date).capitalizeIfNot()
    } catch (e: Exception) {
        this // Devuelve la cadena original si hay algún error en el parseo
    }
}

//Tambien se crea otra 'extension function' para capitalizar las palabras si no estan capitalizadas
fun String.capitalizeIfNot(): String {
    return if (this.isNotEmpty() && !this[0].isUpperCase()) {
        this.replaceFirstChar { it.uppercase() }
    } else {
        this
    }
}

//Esta 'extension function' obtiene un clase procedente de un intent
@Suppress("DEPRECATION")
fun <T : Serializable?> Intent.getSerializable(key: String, m_class: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        this.getSerializableExtra(key, m_class)!!
    else
        this.getSerializableExtra(key) as T
}