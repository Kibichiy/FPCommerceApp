package com.example.fpcommerceapp.view.cart.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fpcommerceapp.R
import com.example.fpcommerceapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}