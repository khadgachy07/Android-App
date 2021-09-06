package com.khadga.meromedapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khadga.meromedapp.entity.Product

@Dao
interface ProductDAO {
    // insert product
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product : Product)

    @Query("select * from Product")
    suspend fun getAllProduct(): List<Product>

    @Query("delete from Product")
    suspend fun deleteProducts()
}