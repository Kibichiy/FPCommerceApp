package com.example.fpcommerceapp.view.cart.database

import androidx.room.*

@Dao
interface CartDao {
    @get: Query("SELECT * FROM CartEntity")
    val allItems: Array<CartEntity>

    /*@Query("DELETE FROM CartEntity")*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(item: CartEntity)

    @Delete
    fun deleteItem(Item: CartEntity)
}