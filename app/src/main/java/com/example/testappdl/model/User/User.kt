package com.example.testappdl.model.User

import com.example.testappdl.model.User.localrep.entity.UserRoom
import com.example.testappdl.model.User.remoteRep.UserRetrofit

data class User(
    val name:String,
    val surname:String,
    val age:Int,
    val dataDestination: DataDestination
){
    sealed class DataDestination(val mark:String){
        object Remote: DataDestination("REMOTE")
        object Local: DataDestination("LOCAL")

    }

    companion object {
        fun roomToData(userRoom: UserRoom):User{
            return User(userRoom.name,
                userRoom.surname,
                userRoom.age,
                dataDestination = DataDestination.Local
            )
        }

        fun retrofitToData(userRetrofit: UserRetrofit): User {
            return User(
                name = userRetrofit.name.first,
                surname = userRetrofit.name.last,
                age = userRetrofit.dob.age,
                dataDestination = DataDestination.Remote
            )
        }
    }
}