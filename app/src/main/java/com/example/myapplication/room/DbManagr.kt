package com.example.mvvm_simple.db

import androidx.room.Room
import com.example.myapplication.app.App


object DbManagr {
    private var dao: AppDataBase? = null

    val instance: AppDataBase
        @Synchronized get() {
            if (DbManagr.dao == null) {
                dao = Room.databaseBuilder(
                    App.ApplicationINSTANCE,
                    AppDataBase::class.java, "UI")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return dao!!
        }


}