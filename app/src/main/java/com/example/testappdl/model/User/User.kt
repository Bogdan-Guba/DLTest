package com.example.testappdl.model.User

import com.example.testappdl.model.User.localrep.entity.UserRoom
import com.example.testappdl.model.User.remoteRep.UserRetrofit

data class User(
    val id: String,
    val name:String,
    val surname:String,
    val age:Int,
    val dataDestination: DataDestination
){
    enum class DataDestination(val mark:String){
        REMOTE("REMOTE"),
        LOCAL("LOCAL")

    }

    companion object {
        fun roomToData(userRoom: UserRoom):User{
            return User(userRoom.ID.toString(),userRoom.name,
                userRoom.surname,
                userRoom.age,
                dataDestination = DataDestination.LOCAL
            )
        }

        fun retrofitToData(userRetrofit: UserRetrofit): User {
            return User(
                id = userRetrofit.email.hashCode().toString(),
                name = userRetrofit.name.first,
                surname = userRetrofit.name.last,
                age = userRetrofit.dob.age,
                dataDestination = DataDestination.REMOTE
            )
        }
    }
}