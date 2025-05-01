package com.example.testappdl.rep

import com.example.testappdl.rep.localrep.entity.UserRoom
import com.example.testappdl.rep.remoteRep.UserRetrofit

data class User(
    val name:String,
    val surname:String,
    val age:Int
)

interface ConvertToUser{
    fun roomToData(userRoom: UserRoom):User{
        return User(userRoom.firstName, userRoom.lastName , userRoom.age)
    }

    fun retrofitToData(userRetrofit: UserRetrofit):User{
        return User(userRetrofit.name.first, userRetrofit.name.last, userRetrofit.dob.age)
    }
}
