package com.example.testappdl.rep.remoteRep


import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET




data class UserRetrofit(
    val name: Name,
    val dob: Dob
)

data class Name(
    val first: String,
    val last: String
)

data class Dob(
    val age: Int
)

interface ApiService {
    @GET("api/")
    suspend fun getUser(): Response<UserRetrofit>
}

private const val BASE_URL = "https://randomuser.me/api/"

val instance: ApiService by lazy {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(ApiService::class.java)
}
