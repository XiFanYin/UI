package com.example.myapplication.base

/**
 * Created by Administrator on 2018/5/21.
 */
interface BaseView {

    //显示加载提示
    fun showLoading()

    //隐藏加载提示
    fun dissmassLoading()

    //显示错误提示布局
    fun showErrorView()

    //隐藏显示错误提示布局
    fun dissmassErrorView()

    /*显示没网的dialog*/
    fun  showNoNetDialog()

    /*隐藏没网的dialog*/
    fun  dismissNoNetDialog()

    /*加载更多失败*/
    fun  LoadMoreError()







}