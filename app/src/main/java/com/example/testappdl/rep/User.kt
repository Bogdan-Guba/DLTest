package com.example.testappdl.rep

import com.example.testappdl.rep.localrep.entity.UserRoom
import com.example.testappdl.rep.remoteRep.UserRetrofit
import javax.inject.Inject

data class User(
    val name:String?,
    val surname:String?,
    val age:Int?
){
    companion object {
        fun roomToData(userRoom: UserRoom):User{
            return User(userRoom.firstName, userRoom.lastName , userRoom.age)
        }

        fun retrofitToData(userRetrofit: UserRetrofit): User {
            return User(
                name = userRetrofit.name?.first ?: "Unknown", // Безопасное обращение к first
                surname = userRetrofit.name?.last ?: "",          // Безопасное обращение к last
                age = userRetrofit.dob?.age ?: 0                   // Безопасное обращение к age
            )
        }
    }
}