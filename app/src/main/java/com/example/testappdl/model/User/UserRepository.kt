package com.example.testappdl.model.User

import android.util.Log
import com.example.testappdl.model.User.localrep.dao.UserDao
import com.example.testappdl.model.User.remoteRep.ApiService
import com.example.testappdl.model.User.remoteRep.UserRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
     val userDao: UserDao,
     val retrofit: ApiService
) {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users.asStateFlow()

    init {

        CoroutineScope(Dispatchers.IO).launch {
            val retrofitUsers = getUsersFromRetrofit().map { User.retrofitToData(it) }
            userDao.getAll().map { roomList ->
                roomList.map { User.roomToData(it) }
            }.collect { roomUsers ->
                val combined = (retrofitUsers + roomUsers).asReversed()
                _users.value = combined
            }


        }
    }

    suspend fun deleteAllUserData(){
        userDao.deleteUsers()
        _users.value=emptyList()
        Log.e("TEST",_users.value.toString())

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
