package com.example.fpcommerceapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fpcommerceapp.R
import com.example.fpcommerceapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)
    }
}