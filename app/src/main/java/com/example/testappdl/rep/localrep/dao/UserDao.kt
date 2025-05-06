package com.example.testappdl.rep.localrep.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testappdl.rep.localrep.entity.UserRoom
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response


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



}