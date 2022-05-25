package com.example.fpcommerceapp.presenter

import com.example.fpcommerceapp.model.User

interface RegistrationMVP {
    interface RegistrationView{
        fun setResult(message:String)
        fun onLoad(isloading:Boolean)
    }
    interface RegistrationPresenter{
        fun registerUser(user: User):String
    }
}