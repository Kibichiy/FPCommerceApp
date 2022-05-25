package com.example.fpcommerceapp.view.loginitems

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fpcommerceapp.R
import com.example.fpcommerceapp.databinding.ActivityRegistrationBinding
import com.example.fpcommerceapp.model.User
import com.example.fpcommerceapp.model.VolleyHandler
import com.example.fpcommerceapp.presenter.RegistrationMVP
import com.example.fpcommerceapp.presenter.RegistrationPresenter

class RegistrationActivity : AppCompatActivity(), RegistrationMVP,RegistrationMVP.RegistrationView {
    private lateinit var binding:ActivityRegistrationBinding
    private lateinit var presenter: RegistrationPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val volleyHandler = VolleyHandler(this)
        presenter =RegistrationPresenter(volleyHandler, this)
        initviews()


    }

    private fun initviews() {
        binding.apply {
            registerBtn.setOnClickListener {
                presenter.registerUser(
                    User(
                        edtxFullname.text.toString(),
                        edtxMobile.text.toString(),
                        edtxEmail.text.toString(),
                        edtxPassword.text.toString()
                    )
                )
            }
        }
        binding.loginClickable.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    override fun setResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding.apply {
            edtxFullname.text!!.clear()
            edtxMobile.text!!.clear()
            edtxEmail.text!!.clear()
            edtxPassword.text!!.clear()
        }
    }

    override fun onLoad(isloading: Boolean) {
        if(isloading){
            Toast.makeText(this, "Wait....", Toast.LENGTH_SHORT).show()
        }
    }
}