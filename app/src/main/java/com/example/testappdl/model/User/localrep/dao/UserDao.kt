package com.example.testappdl.model.User.localrep.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testappdl.model.User.localrep.entity.UserRoom
import kotlinx.coroutines.flow.Flow


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
    fun getAll(): Flow<List<UserRoom>>



    @Query("DELETE FROM Users")
    suspend fun deleteUsers()



}