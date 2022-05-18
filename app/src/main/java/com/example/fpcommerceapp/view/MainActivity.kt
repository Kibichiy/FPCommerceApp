package com.example.fpcommerceapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fpcommerceapp.R
import com.example.fpcommerceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            createAcntClickable.setOnClickListener {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_1, RegisterFragment(), null)
                    .commit()
            }
        }
    }
}