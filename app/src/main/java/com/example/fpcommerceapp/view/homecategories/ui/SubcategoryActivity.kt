package com.example.fpcommerceapp.view.homecategories.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fpcommerceapp.databinding.ActivitySubcategoryBinding
import com.example.fpcommerceapp.model.Constants
import com.example.fpcommerceapp.model.VolleyHandler
import com.example.fpcommerceapp.view.cart.database.AppDatabase
import com.example.fpcommerceapp.view.homecategories.adapters.SubcategoriesAdapter
import com.example.fpcommerceapp.view.homecategories.adapters.CategoriesAdapter.Companion.SUB_DATA
import com.example.fpcommerceapp.view.homecategories.dataclass.Subcategories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SubcategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubcategoryBinding
    private lateinit var adapter: SubcategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubcategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getDetails()
        binding.recyclerViewSub.layoutManager = LinearLayoutManager(this@SubcategoryActivity)
    }

    private fun getDetails() {
        val subcategoryId= intent.extras?.get(SUB_DATA)


        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val url = "${Constants.BASE_URL}${Constants.SUB_CATEGORIES}${subcategoryId}"
        var message: String? = null

        val request = StringRequest(
            Request.Method.GET, url, { apiResponse: String ->
                val typeToken = object : TypeToken<Subcategories>() {}
                val gson = Gson()
                Log.d("tag", "$apiResponse")
                try {
                    val response: Subcategories = gson.fromJson(apiResponse, typeToken.type)
                    val resultcategories = response.subcategories

                    if (response.status == 0) {
                        binding.apply {
                            adapter = SubcategoriesAdapter(baseContext, resultcategories)

                        }
                        binding.recyclerViewSub.adapter = adapter
                    } else {
                        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, { error: VolleyError ->
                error.printStackTrace()
                message = error.message.toString()
                Log.i(VolleyHandler.TAG, error.toString())
                Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }
}
