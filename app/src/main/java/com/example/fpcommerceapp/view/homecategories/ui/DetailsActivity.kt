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
import com.example.fpcommerceapp.databinding.ActivityDetailsBinding
import com.example.fpcommerceapp.model.Constants
import com.example.fpcommerceapp.model.VolleyHandler
import com.example.fpcommerceapp.view.homecategories.adapters.CategoriesAdapter
import com.example.fpcommerceapp.view.homecategories.adapters.DetailsAdapter
import com.example.fpcommerceapp.view.homecategories.adapters.SubcategoriesAdapter
import com.example.fpcommerceapp.view.homecategories.dataclass.Details
import com.example.fpcommerceapp.view.homecategories.dataclass.Subcategories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var adapter:DetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()
        binding.recyclerViewDetails.layoutManager = LinearLayoutManager(this@DetailsActivity)
    }

    private fun fetchData() {
        val subcategoryId = intent.extras?.get(SubcategoriesAdapter.DETAILS_DATA)


        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val url = "${Constants.BASE_URL}${Constants.DETAILS_END}${subcategoryId}"
        var message: String? = null

        val request = StringRequest(
            Request.Method.GET, url, { apiResponse: String ->
                val typeToken = object : TypeToken<Details>() {}
                val gson = Gson()
                Log.d("tag", "$apiResponse")
                try {
                    val response: Details = gson.fromJson(apiResponse, typeToken.type)
                    val detailsresult = response.products

                    if (response.status == 0) {
                        binding.apply {
                            adapter = DetailsAdapter(baseContext, detailsresult)
                            binding.recyclerViewDetails.adapter = adapter
                        }

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