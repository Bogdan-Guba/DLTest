package com.example.testappdl.rep.localrep.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testappdl.rep.localrep.entity.UserRoom
import dagger.Provides


@Dao
interface UserDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserRoom)

    @Insert
    suspend fun insertListOfUser(listOfUser:List<UserRoom>){
        for( product in listOfUser ){
            insertUser(product)
        }
    }

    @Query("SELECT * FROM Users")
    suspend fun getAll(): MutableList<UserRoom>



}