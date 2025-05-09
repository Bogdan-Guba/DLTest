package com.example.testappdl.model.User.localrep.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testappdl.model.User.User
import com.example.testappdl.model.User.localrep.entity.UserRoom


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
    fun getAll(): List<UserRoom>



    @Query("DELETE FROM Users")
    suspend fun deleteAllUsers()

    @Query("DELETE FROM Users WHERE ID = :id")
    suspend fun deleteUserByID(id: String)






}