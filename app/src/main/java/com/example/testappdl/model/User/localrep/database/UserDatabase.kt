package com.example.testappdl.model.User.localrep.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testappdl.model.User.localrep.dao.UserDao
import com.example.testappdl.model.User.localrep.entity.UserRoom

@Database(entities =[UserRoom::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun productDao(): UserDao
}