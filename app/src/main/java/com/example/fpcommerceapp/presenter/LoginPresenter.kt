package com.example.fpcommerceapp.presenter

import com.example.fpcommerceapp.model.User
import com.example.fpcommerceapp.model.VolleyHandler


class LoginPresenter(
    private val volleyHandler: VolleyHandler,
    private val loginView: LoginMVP.LoginView
) : LoginMVP.LoginPresenter {


    override fun loginUser(email: String, password: String): String {
        loginView.onLoad(true)
        val message = volleyHandler.login(email,password, object : LoginCallback {
            override fun onSuccess(message: String,user: User) {
                loginView.onLoad(false)
                loginView.setResult(user)
            }

            override fun onFailure(message: String) {
                loginView.onLoad(false)
                loginView.setError(message)
            }
        })
        return message ?: DEFAULT_MESSAGE
    }

    companion object {
        const val DEFAULT_MESSAGE = "default message"
    }
}