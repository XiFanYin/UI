package com.example.mvvm_simple.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.bean.Girl

@Dao
interface GirlDao {
    /*插入一个列表*/
    @Insert
    fun insertAll(girl:Girl)


    /*判断当前页是否有缓存*/
    @Query("select count(*) from Girl where page==:page" )
    fun hasCache(page:Int):Int


    @Update
    fun updateGirl(girl:Girl)

    /*查询缓存*/
    @Query("select * from Girl where page==:page" )
    fun getCache(page:Int):Girl


}
