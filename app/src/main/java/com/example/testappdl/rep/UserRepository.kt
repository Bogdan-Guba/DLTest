package com.example.testappdl.rep

import android.util.Log
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.collectAsState
import com.example.testappdl.rep.localrep.dao.UserDao
import com.example.testappdl.rep.localrep.entity.UserRoom
import com.example.testappdl.rep.remoteRep.ApiService
import com.example.testappdl.rep.remoteRep.UserRetrofit
import com.example.testappdl.ui.theme.DarkColorScheme
import com.example.testappdl.ui.theme.LightColorScheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
     val userDao: UserDao,
     val retrofit: ApiService
) {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users.asStateFlow()

    private val _colorSheme = MutableStateFlow<ColorScheme>(DarkColorScheme)
    val colorSheme: StateFlow<ColorScheme> get() = _colorSheme.asStateFlow()

    private var retrofitUsers: List<User> = emptyList()

    init {

        CoroutineScope(Dispatchers.IO).launch {
            val networkData = getUsersFromRetrofit()
            retrofitUsers = networkData.map { User.retrofitToData(it) }
            userDao.getAll().map { roomList ->
                roomList.map { User.roomToData(it) }
            }.collect { roomUsers ->
                val combined = (retrofitUsers + roomUsers).asReversed()
                _users.value = combined
            }


        }
    }

    fun changeTheme(){
        if(colorSheme.value==DarkColorScheme){
            _colorSheme.value= LightColorScheme
        }
        else
        {
            _colorSheme.value= DarkColorScheme
        }
    }

    fun getUsersFromRoom(): Flow<List<User>> {
        return userDao.getAll().map { list -> list.map { User.roomToData(it) } }
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
