package com.example.testappdl.rep.localrep.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val ID:Int=0,
    @ColumnInfo(name =" First") val firstName:String,
    @ColumnInfo(name =" Last") val lastName:String,
    @ColumnInfo(name = "Age") val age:Int
)

