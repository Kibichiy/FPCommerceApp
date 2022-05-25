package com.example.fpcommerceapp.presenter

import com.example.fpcommerceapp.model.User
import com.example.fpcommerceapp.model.VolleyHandler

class RegistrationPresenter (
    private val volleyHandler: VolleyHandler,
    private val registrationView: RegistrationMVP.RegistrationView
    ): RegistrationMVP.RegistrationPresenter {

        override fun registerUser(user: User): String {
            registrationView.onLoad(true)
            val message = volleyHandler.userRegistration(user, object :OperationalCallBack{

                override fun onSuccess(message: String) {
                    registrationView.onLoad(false)
                    registrationView.setResult(message)
                }
                override fun onFailure(message: String) {
                    registrationView.onLoad(false)
                    registrationView.setResult(message)
                }
            })
            return message.toString()
        }
}