package com.example.myapplication

import com.example.myapplication.base.BasePresenter
import com.example.myapplication.base.BaseView
import com.example.myapplication.bean.Girl

interface MainContact {

    interface View : BaseView {

        fun setData(girl: Girl ,hasMore:Boolean)

        fun  setMoreData(girl: Girl,hasMore:Boolean)
    }

    interface Persenter : BasePresenter {

        fun getData(page:Int)


        fun getMoreData(page: Int)

    }



}