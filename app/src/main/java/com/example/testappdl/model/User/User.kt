package com.example.testappdl.model.User

import com.example.testappdl.model.User.localrep.entity.UserRoom
import com.example.testappdl.model.User.remoteRep.UserRetrofit

data class User(
    val name:String,
    val surname:String,
    val age:Int
){
    companion object {
        fun roomToData(userRoom: UserRoom):User{
            return User(userRoom.name, userRoom.surname , userRoom.age)
        }

        fun retrofitToData(userRetrofit: UserRetrofit): User {
            return User(
                name = userRetrofit.name.first,
                surname = userRetrofit.name.last,
                age = userRetrofit.dob.age
            )
        }
    }
}