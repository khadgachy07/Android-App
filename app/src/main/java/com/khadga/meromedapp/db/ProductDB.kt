package com.khadga.meromedapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khadga.meromedapp.dao.CartDAO
import com.khadga.meromedapp.dao.ProductDAO
import com.khadga.meromedapp.dao.UserDAO
import com.khadga.meromedapp.entity.Cart
import com.khadga.meromedapp.entity.Product
import com.khadga.meromedapp.entity.User

@Database(
    entities = [(Product::class), (Cart::class), (User::class)],
    version = 1
)
abstract class ProductDB : RoomDatabase() {

    abstract fun getProductDAO(): ProductDAO
    abstract fun getCartDAO() : CartDAO
    abstract fun getUserDAO(): UserDAO

    companion object {
        @Volatile
        private var instance: ProductDB? = null
        fun getInstance(context: Context): ProductDB {
            if (instance == null) {
                synchronized(ProductDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context): ProductDB {
            return Room.databaseBuilder(
                context.applicationContext,
                ProductDB::class.java,
                "MeroMedDatabase"
            ).build()
        }
    }
}