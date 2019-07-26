package com.example.myapplication.bean

import androidx.room.*
import com.example.myapplication.utils.fromJson
import com.example.myapplication.utils.toJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
@TypeConverters(Converter::class)
data class Girl( val error: Boolean, var results: List<Result>) {

    @PrimaryKey
    var page = -1

}


data class Result(
    val _id: String,
    val createdAt: String,
    val desc: String,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String
)


class Converter {

    // FocusBean.Item
    @TypeConverter
    fun storeItemToString(data: List<Result>): String = data.toJson()

    @TypeConverter
    fun storeStringToItem(value: String): List<Result> = value.fromJson()





}