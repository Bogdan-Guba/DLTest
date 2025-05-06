package com.example.testappdl.rep.remoteRep


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


data class RandomUserResponse(
    val results: List<UserRetrofit>,
    val info: Info
)

data class UserRetrofit(
    val name: Name,
    val dob: Dob,
    val email: String,
    val picture: Picture
)

data class Name(
    val first: String,
    val last: String
)

data class Dob(
    val age: Int
)

data class Picture(
    val large: String
)

data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
interface ApiService {
    @GET("/api")
    suspend fun getMultipleUsers(
        @Query("results") count: Int = 10,
        @Query("seed") seed: String = "test"
    ): Response<RandomUserResponse>
}


