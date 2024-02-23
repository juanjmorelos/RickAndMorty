package com.apexglobalmobility.rickymorty.viewModel

import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import com.apexglobalmobility.rickymorty.entities.character.Character
import com.apexglobalmobility.rickymorty.service.OnServiceResponseListener
import com.apexglobalmobility.rickymorty.service.ServicePackageManager
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject

class CharacterViewModel() : ViewModel() {

    // Representa la lista de personajes recuperados del servicio.
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> get() = _characters

    // Indica si la carga de personajes está en curso.
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Referencia a la instancia de ServicePackageManager para realizar solicitudes al servicio.
    private lateinit var servicePackageManager: ServicePackageManager

    // Lista local para almacenar personajes mientras se procesan.
    private val _charactersLocal = mutableListOf<Character>()

    // Representa eventos relacionados con la operación del ViewModel.
    val events = MutableStateFlow<CharacterEvent?>(null)

    // Página actual que se está cargando.
    var page = 1
    // Se declara para guardar el número de páginas disponibles para cargar
    var maxPages = 1

    //Metodo para realizar la petición al servicio y obtener los personajes
    fun fetchCharacters(servicePackageManager: ServicePackageManager) {
        this.servicePackageManager = servicePackageManager
        _isLoading.value = true

        servicePackageManager.sendGetRequestToService("character", object : OnServiceResponseListener {
            override fun onSuccessResponse(response: JSONObject?) {
                //Se procesa la respuesta dada por el servicio y se actualiza la lista de personajes para la vista
                val parsedCharacters = parseResponse(response)
                //Se obtiene el número de páginas disponibles para cargar, por defecto se dejara una pagina
                maxPages = response?.getJSONObject("info")?.optInt("pages") ?: 1
                _characters.value = parsedCharacters
                _isLoading.value = false
                page++
            }

            override fun onErrorResponse(error: VolleyError?) {
                //Si ocurre un error durante la petición se muestra un mensaje al usuario
                _isLoading.value = false
                events.value = CharacterEvent.ToastMessage("An error occurred while loading the characters.")
            }
        })
    }

    //Función para cargar la siguiente página
    fun loadNextPage() {
        //Se valida si se llegó a la última página para evitar realizar una petición
        if (canLoadMorePages()) {
            _isLoading.value = true

            servicePackageManager.sendGetRequestToService("character?page=$page", object : OnServiceResponseListener {
                override fun onSuccessResponse(response: JSONObject?) {
                    //Se procesa la respuesta dada por el servicio y se actualiza la lista de personajes para la vista agregando
                    //los personajes de la siguiente página
                    val parsedCharacters = parseResponse(response)
                    _characters.value = parsedCharacters
                    _isLoading.value = false
                    page++
                }

                override fun onErrorResponse(error: VolleyError?) {
                    //Si ocurre un error durante la petición se muestra un mensaje al usuario
                    _isLoading.value = false
                    events.value = CharacterEvent.ToastMessage("An error occurred while loading the characters.")
                }
            })
        } else {
            events.value = CharacterEvent.ToastMessage("No more pages to load")
        }
    }

    private fun canLoadMorePages(): Boolean {
        return page <= maxPages
    }

    private fun parseResponse(response: JSONObject?): List<Character> {
        //Se obtiene el data del json
        val dataArray = response?.optJSONArray("results") ?: return mutableListOf()
        //Se recorre el json y se mapea con Gson para convertir el json a una clase más manejable
        for (i in 0 until dataArray.length()) {
            val jsonCharacter = dataArray.getJSONObject(i)
            val characterEntity = GsonBuilder().create().fromJson(jsonCharacter.toString(), Character::class.java)
            _charactersLocal.add(characterEntity)
        }

        return _charactersLocal
    }
}
