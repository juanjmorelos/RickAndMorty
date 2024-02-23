package com.apexglobalmobility.rickymorty.viewModel

sealed class CharacterEvent {
    data class ToastMessage(val message: String) : CharacterEvent()
}