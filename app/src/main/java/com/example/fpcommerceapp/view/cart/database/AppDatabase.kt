package com.example.fpcommerceapp.view.cart.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fpcommerceapp.view.homecategories.adapters.DetailsAdapter

@Database(
    entities = [CartEntity::class,],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase() : RoomDatabase() {


    abstract fun cartDao(): CartDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "cartTable")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }
}