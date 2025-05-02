package com.example.testappdl.modules


import android.content.Context
import androidx.room.Room
import com.example.testappdl.rep.ConvertToUser
import com.example.testappdl.rep.ConvertToUserImpl
import com.example.testappdl.rep.localrep.dao.UserDao
import com.example.testappdl.rep.localrep.database.UserDatabase
import com.example.testappdl.rep.remoteRep.ApiService
import dagger.Binds
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
            .baseUrl("https://your-api-url.com/")
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

@Module
@InstallIn(SingletonComponent::class)
interface ConvertModule {

    @Binds
    fun bindConverter(impl: ConvertToUserImpl): ConvertToUser
}