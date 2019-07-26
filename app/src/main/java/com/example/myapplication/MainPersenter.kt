package com.example.myapplication

import android.util.Log
import com.example.mvvm_simple.db.DbManagr
import com.example.myapplication.base.BasePresenterImpl
import com.example.myapplication.bean.Girl
import com.example.myapplication.net.BaseObserver
import com.example.myapplication.net.RetrofitUtil
import com.example.myapplication.utils.RxSchedulers
import com.example.myapplication.utils.toJson
import com.example.myapplication.utils.toast
import com.google.gson.Gson
import io.reactivex.disposables.Disposable
import xifuyin.tumour.com.a51ehealth.kotlin_simple.net.api.API


class MainPersenter(view: MainContact.View) :BasePresenterImpl<MainContact.View>(view),MainContact.Persenter {



    override fun getData(page: Int) {
        /*首先获取缓存*/
        getOneCache(page)

        /*请求网络*/
        RetrofitUtil.create(API::class.java)
            .getItem(1)
            .doOnNext {
                DbManagr.instance.runInTransaction {
                    if(hasCache(page)){
                        it.page = page
                        DbManagr.instance.getGirlDao().updateGirl(it)
                    }else{
                        it.page = page
                        DbManagr.instance.getGirlDao().insertAll(it)
                    }
                }
            }
            .subscribeOn(RxSchedulers.io)
            .observeOn(RxSchedulers.ui)
            .doOnError {
                if (!NetWorkUtils.isNetworkReachable()){
                    //这里表示没网发生错误,去显示没网的dialog
                    mView?.showNoNetDialog()
                }else{
                    toast {it.message!!}
                }
                if (!hasCache(page)){
                    /*没缓存，error布局提示用户*/
                    mView?.showErrorView()
                }
            }
            .doOnComplete { mView?.dissmassErrorView() }//只要加载正确就显示正确布局
            .subscribe(object : BaseObserver<Girl>(){
                override fun onSubscribe(p0: Disposable) {
                    addDisposable(p0)
                }
                override fun onNext(t: Girl) {

                    mView?.setData(t,page!=3)
                }
            })
    }

    override fun getMoreData(page: Int) {

        getMoreCache(page)

        /*请求网络*/
        RetrofitUtil.create(API::class.java)
            .getItem(page)
            .doOnNext {
                DbManagr.instance.runInTransaction {
                    if(hasCache(page)){
                        it.page = page
                        DbManagr.instance.getGirlDao().updateGirl(it)
                    }else{
                        it.page = page
                        DbManagr.instance.getGirlDao().insertAll(it)
                    }
                }
            }
            .subscribeOn(RxSchedulers.io)
            .observeOn(RxSchedulers.ui)
            .doOnError {
                if (!NetWorkUtils.isNetworkReachable()){
                    //这里表示没网发生错误,去显示没网的dialog
                    mView?.showNoNetDialog()
                }
                    toast {it.message!!}

                mView?.LoadMoreError()
            }
            .subscribe(object : BaseObserver<Girl>(){
                override fun onSubscribe(p0: Disposable) {
                    addDisposable(p0)
                }
                override fun onNext(t: Girl) {
                    mView?.setMoreData(t,page!=3)
                }
            })
    }



    /*判断是否有缓存*/
    private fun hasCache(page:Int)= DbManagr.instance.getGirlDao().hasCache(page)!=0

    /*获取第一页缓存*/
    private  fun getOneCache(page:Int){
        /*如果有缓存先更新UI*/
          if (hasCache(page)) {
            val girl = DbManagr.instance.getGirlDao().getCache(page)
              mView?.setData(girl,page!=3)
          }
    }
    /*获取其他页缓存*/
    private  fun getMoreCache(page:Int){
        /*如果有缓存先更新UI*/
        if (hasCache(page)) {
            val girl = DbManagr.instance.getGirlDao().getCache(page)
            mView?.setMoreData(girl,page!=3)
        }
    }

}