package com.example.myapplication.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun <T> T.toJson(): String {
    return Gson().toJson(this)
}



inline fun <reified T> String.fromJson(): T {
    val listType = object : TypeToken<T>() {

    }.type
    return  Gson().fromJson(this,listType)
}
