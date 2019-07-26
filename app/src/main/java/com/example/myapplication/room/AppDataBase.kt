package com.example.mvvm_simple.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.bean.Girl


@Database(entities = [Girl::class],version = 1,exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract  fun getGirlDao():GirlDao

}