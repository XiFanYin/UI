package com.example.myapplication.utils


import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxSchedulers {

    val database: Scheduler
        get() = Schedulers.single()

    val io: Scheduler
        get() = Schedulers.io()
    
    val ui: Scheduler
        get() = AndroidSchedulers.mainThread()
}