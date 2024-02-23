package com.apexglobalmobility.rickymorty.service

import com.android.volley.VolleyError
import org.json.JSONException
import org.json.JSONObject

interface OnServiceResponseListener {
    @Throws(JSONException::class)
    fun onSuccessResponse(response: JSONObject?)

    fun onErrorResponse(error: VolleyError?)
}