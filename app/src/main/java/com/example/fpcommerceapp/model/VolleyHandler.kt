package com.example.fpcommerceapp.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fpcommerceapp.presenter.LoginCallback
import com.example.fpcommerceapp.presenter.OperationalCallBack
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.net.URLEncoder

class VolleyHandler(context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun userRegistration(user: User, callBack: OperationalCallBack): String? {
        val url = "${Constants.BASE_URL}${Constants.REGISTRATION}"
        val data = JSONObject()
        var message: String? = null

        data.put("full_name", user.name)
        data.put("mobile_no", user.mobile)
        data.put("email_id", user.email)
        data.put("password", user.password)

        val request = JsonObjectRequest(Request.Method.POST, url, data, { response: JSONObject ->
            message = response.getString("message")
            Log.i(TAG, message.toString())
            callBack.onSuccess(message.toString())
        }, { error: VolleyError ->
            error.printStackTrace()
            message = error.message.toString()
            Log.i(TAG, error.toString())
            callBack.onFailure(message.toString())
        })
        requestQueue.add(request)
        return message
    }
    fun login(email:String, password:String, callBack: LoginCallback):String?{
        val url = "${Constants.BASE_URL}${Constants.LOGIN_END_POINT}"
        val data = JSONObject()
        var message: String? = null

        data.put("email_id", email)
        data.put("password", password)

        val request = JsonObjectRequest(Request.Method.POST, url, data, { response: JSONObject ->
            message = response.getString("message")
            Log.i(TAG, message.toString())

            val userObj = response.getJSONObject("user")
            val user = User(
                userObj.getString("user_id"),
                userObj.getString("full_name"),
                userObj.getString("mobile_no"),
                userObj.getString("email_id")
            )
            callBack.onSuccess(message.toString(),user)

        }, { error: VolleyError ->
            error.printStackTrace()
            message = error.message.toString()
            Log.i(TAG, error.toString())
            callBack.onFailure(message.toString())
        })
        requestQueue.add(request)
        return message
    }
    companion object {
        const val TAG = "TAG"
    }
}