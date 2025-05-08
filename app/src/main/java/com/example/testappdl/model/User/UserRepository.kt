package com.example.testappdl.model.User

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.testappdl.model.User.localrep.dao.UserDao
import com.example.testappdl.model.User.localrep.entity.UserRoom
import com.example.testappdl.model.User.remoteRep.ApiService
import com.example.testappdl.model.User.remoteRep.UserRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
     val userDao: UserDao,
     val retrofit: ApiService
) {

    private val _users = MutableStateFlow<List<User>>(mutableListOf())
    val users: StateFlow<List<User>> get() = _users.asStateFlow()

    init {

        CoroutineScope(Dispatchers.IO).launch {
            val retrofitUsers = getUsersFromRetrofit().map { User.retrofitToData(it) }
            val roomUsers = userDao.getAll().map { User.roomToData(it) }
                val combined = (retrofitUsers + roomUsers).asReversed()
                _users.value = combined.toMutableList()


        }
    }

    suspend fun addUser(userRoom: UserRoom){
        userDao.insertUser(userRoom)
        val updatedList:List<User> = _users.value.reversed() + User.roomToData(userRoom)
        _users.value= updatedList.reversed()




    }

    suspend fun deleteAllUserData(){
        userDao.deleteUsers()
        _users.value=mutableListOf()


    }
    suspend fun deleteUser(user: User){
        if(user.dataDestination== User.DataDestination.Local){
            userDao.deleteUser(user)
            val updatedList = _users.value.filter { it != user }
           _users.value= updatedList
        }else{
            val updatedList = _users.value.filter { it != user }
            _users.value= updatedList
        }
    }




    suspend fun getUsersFromRetrofit(): List<UserRetrofit> {
        return try {
            val response = retrofit.getMultipleUsers()
            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                Log.e("API", "Error: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("API", "Network error", e)
            emptyList()
        }
    }
}
