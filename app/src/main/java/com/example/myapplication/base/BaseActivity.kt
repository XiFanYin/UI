package com.example.myapplication.base

import android.app.Activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.gyf.immersionbar.ktx.immersionBar


/**
 * Created by Administrator on 2018/5/21.
 *
 * Activity基类
 *
 */
abstract class BaseActivity : AppCompatActivity() {
    //延迟初始化
    lateinit var act: Activity




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        act = this
        //设制竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //设置布局
        setContentView(getLayout())
        //初始化状态栏
        initBar()
        //初始化监听
        initListener()
        //获取服务器数据
        getSerivceData()
    }



  open  fun initBar() {
      immersionBar {
          statusBarColor(android.R.color.white)
          statusBarDarkFont(true)
      }
    }

    //============================和布局有关的方法==================================


    abstract fun getLayout(): Int


    //========================和设置监听有关的方法==============================


    protected abstract fun initListener()

    //========================和获取数据有关的方法==============================

   open fun getSerivceData() {}



}