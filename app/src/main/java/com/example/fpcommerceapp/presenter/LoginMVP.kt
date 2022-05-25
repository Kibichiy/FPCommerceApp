package com.example.fpcommerceapp.presenter

import com.example.fpcommerceapp.model.User


class LoginMVP {
    interface LoginView {
        fun setError(errorMessage:String)
        fun setResult(user: User)
        fun onLoad(isLoading: Boolean)
    }

    interface LoginPresenter {
        fun loginUser(email:String,password:String): String
    }
}