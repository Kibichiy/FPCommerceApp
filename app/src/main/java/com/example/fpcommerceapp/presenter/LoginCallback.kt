package com.example.fpcommerceapp.presenter

import com.example.fpcommerceapp.model.User


interface LoginCallback {
    fun onSuccess(message: String,user: User)
    fun onFailure(message: String)
}