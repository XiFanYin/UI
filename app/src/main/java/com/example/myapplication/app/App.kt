package com.example.myapplication.app

import android.app.Application
import com.example.myapplication.R
import com.facebook.stetho.Stetho
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

class App : Application() {

    companion object{

        lateinit var ApplicationINSTANCE: App

    }
    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(android.R.color.white, android.R.color.black)
            ClassicsHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(context).setDrawableSize(20F)

        }

    }


    override fun onCreate() {
        super.onCreate()
        ApplicationINSTANCE =this;
        /*数据库调试工具初始化*/
        Stetho.initializeWithDefaults(this);
    }


}