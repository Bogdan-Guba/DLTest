package com.example.testappdl.rep

import com.example.testappdl.rep.localrep.dao.UserDao
import com.example.testappdl.rep.localrep.entity.UserRoom
import com.example.testappdl.rep.remoteRep.ApiService
import com.example.testappdl.rep.remoteRep.UserRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
  val userDao: UserDao,
  val retrofit: ApiService,
  val convertor: ConvertToUser
   )
{
    var users = MutableStateFlow<List<User>>(emptyList())

    init {
        CoroutineScope(Dispatchers.IO).launch {
//            userDao.insertUser(UserRoom(0,"Bogdan","Huba",21))
//            userDao.insertUser(UserRoom(0,"1","2",56))
//            userDao.insertUser(UserRoom(0,"2","1",49))
//            userDao.insertUser(UserRoom(0,"25","12",23))
            users.value = getUnificatedUsers()
        }
    }

    suspend fun getUsersFromRoom(): MutableStateFlow<MutableList<UserRoom>>{
        val users = userDao.getAll()
        return MutableStateFlow(users)

    }

    suspend fun getUsersFromRetrofit(): List<UserRetrofit>{
        var result: MutableList<UserRetrofit> = mutableListOf()
        for(i in 0..5){
            result.add(retrofit.getUser().body()!!)

        }
        return result

    }

    suspend fun getUnificatedUsers() {

        var result: MutableList<User> = mutableListOf()
        val usersRoom = getUsersFromRoom()
        //val usersRetrofit = getUsersFromRetrofit()
        for (users in usersRoom.value){
            result.add(convertor.roomToData(users))
        }
//        for (users in usersRetrofit){
//            result.add(convertor.retrofitToData(users))
//
//        }


        return result
    }

}