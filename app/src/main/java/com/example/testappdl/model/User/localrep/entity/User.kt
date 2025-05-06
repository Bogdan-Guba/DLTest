package com.example.testappdl.model.User.localrep.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val ID:Int=0,
    @ColumnInfo(name = "Name" ) val name:String,
    @ColumnInfo(name = "Surname" ) val surname:String,
    @ColumnInfo(name = "Age" ) val age:Int
)