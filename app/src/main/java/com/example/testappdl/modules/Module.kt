package com.example.testappdl.modules


import android.content.Context
import androidx.room.Room
import com.example.testappdl.model.User.localrep.dao.UserDao
import com.example.testappdl.model.User.localrep.database.UserDatabase
import com.example.testappdl.model.User.remoteRep.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
      fun bindApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
   }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                "my-database"
            ).build()
    }


    @Provides
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.productDao()
    }
}