package com.example.fpcommerceapp.view.loginitems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fpcommerceapp.R
import com.example.fpcommerceapp.databinding.ActivityMainBinding
import com.example.fpcommerceapp.model.User
import com.example.fpcommerceapp.model.VolleyHandler
import com.example.fpcommerceapp.presenter.LoginMVP
import com.example.fpcommerceapp.presenter.LoginPresenter
import com.example.fpcommerceapp.presenter.RegistrationPresenter
import com.example.fpcommerceapp.view.homecategories.ui.HomeActivity

class MainActivity : AppCompatActivity(), LoginMVP.LoginView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val volleyHandler = VolleyHandler(this)
        presenter = LoginPresenter(volleyHandler, this)
        setUpEvents()
    }


    override fun onLoad(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                Toast.makeText(this@MainActivity, "Wait....", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setError(errorMessage: String) {
        Toast.makeText(this,"Login Failed!", Toast.LENGTH_SHORT).show()

    }

    override fun setResult(user: User) {
        Toast.makeText(this,"User Logged in!", Toast.LENGTH_SHORT).show()
        binding.apply {
            edtxEmail.text!!.clear()
            edtxPassword.text!!.clear()
        }
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }


    private fun setUpEvents() {

        binding.createAcntClickable.setOnClickListener {
            startActivity(Intent(this@MainActivity,RegistrationActivity::class.java))
        }

        binding.apply {
            loginBtn.setOnClickListener {
                presenter.loginUser(
                    edtxEmail.text.toString(),
                    edtxPassword.text.toString()
                )
            }
        }
    }
}