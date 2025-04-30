package com.example.testappdl.rep.localrep.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testappdl.rep.localrep.dao.UserDao
import com.example.testappdl.rep.localrep.entity.UserRoom

@Database(entities =[UserRoom::class], version = 1,)
abstract class UserDatabase:RoomDatabase() {
    abstract fun productDao(): UserDao
}