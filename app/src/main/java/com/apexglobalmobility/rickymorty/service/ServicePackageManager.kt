package com.apexglobalmobility.rickymorty.service

import android.content.Context
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.TimeoutError
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.apexglobalmobility.rickymorty.R
import com.apexglobalmobility.rickymorty.shared.Constants
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

//Clase para realizar peticiones al servicio con volley
class ServicePackageManager(private val context: Context) {

    //Se realiza un petición con volley se pide por parámetro el nombre del endpoint y se realiza la petición, se utiliza
    // una interfaz con dos metodos uno se ejecutará cuando la petición sea exitosa enviando el json resultante
    // y el otro se ejecutará cuando haya habido un fallo en la petición y enviará el error
    fun sendGetRequestToService(operation: String, listenerRequest: OnServiceResponseListener) {
        val url = Constants.BASE_URL + operation
        val rq = Volley.newRequestQueue(context)
        val request: JsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            Response.Listener<JSONObject> { response: JSONObject? ->
                try {
                    listenerRequest.onSuccessResponse(response)
                } catch (e: JSONException) {
                    throw RuntimeException(e)
                }
            },
            Response.ErrorListener { error: VolleyError? ->
                //Se mostrará un toast en caso de que haya un timeout error
                if (error is TimeoutError) {
                    showToast(context.getString(R.string.timeout_text_message), true, context)
                    return@ErrorListener
                }
                listenerRequest.onErrorResponse(error)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                return HashMap()
            }
        }
        rq.add(request)
    }

    private fun showToast(text: String, longDuration: Boolean, context: Context) {
        val duration = if (longDuration) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(context, text, duration).show()
    }
}
