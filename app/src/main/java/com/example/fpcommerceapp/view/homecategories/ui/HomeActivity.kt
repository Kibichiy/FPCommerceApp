package com.example.fpcommerceapp.view.homecategories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fpcommerceapp.databinding.ActivityHomeBinding
import com.example.fpcommerceapp.view.homecategories.dataclass.CategoryDetails
import com.example.fpcommerceapp.model.Constants
import com.example.fpcommerceapp.model.VolleyHandler
import com.example.fpcommerceapp.view.homecategories.adapters.CategoriesAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: CategoriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoriesData()
        binding.recyclerView.layoutManager = GridLayoutManager(this@HomeActivity,2)

    }

    fun categoriesData() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val url = "${Constants.BASE_URL}${Constants.CATEGORIES_END}"
        var message: String? = null

        val request = StringRequest(
            Request.Method.GET, url , { apiResponse: String ->
                val typeToken = object : TypeToken<CategoryDetails>() {}
                val gson = Gson()
                Log.d("tag","$apiResponse")
                try {
                    val response: CategoryDetails = gson.fromJson(apiResponse, typeToken.type)
                    val categories = response.categories
                    Log.i("tag","$response")
                    if (response.status == 0){
                        binding.apply {
                            adapter = CategoriesAdapter(baseContext,categories)
                            binding.recyclerView.adapter= adapter
                    }

                    }else{
                        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                    }



                }catch (e:Exception){
                    e.printStackTrace()
                }
            },{ error: VolleyError ->
                error.printStackTrace()
                message = error.message.toString()
                Log.i(VolleyHandler.TAG, error.toString())
                Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }
}