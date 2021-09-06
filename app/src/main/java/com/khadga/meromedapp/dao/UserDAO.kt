package com.khadga.meromedapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khadga.meromedapp.entity.User

@Dao
interface UserDAO {
    // insert user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(user : User)

    @Query("select * from User")
    suspend fun getUser(): User

    @Query("delete from User")
    suspend fun deleteUser()
}