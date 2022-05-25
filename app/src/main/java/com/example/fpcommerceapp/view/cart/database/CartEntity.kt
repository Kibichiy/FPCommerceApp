package com.example.fpcommerceapp.view.cart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CartEntity (
    @PrimaryKey(autoGenerate = true) var pid:Int? = null,
    @ColumnInfo(name = "product_name") var name:String,
    @ColumnInfo(name = "description") var desc:String,
    @ColumnInfo(name = "price") var price:String,
    @ColumnInfo(name = "product_image_url") var image:String
        ){
    constructor(name: String,desc: String,price: String,image: String) : this(+1, name, desc, price, image)
}


