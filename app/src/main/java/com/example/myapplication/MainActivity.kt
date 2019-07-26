package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.base.BaseMvpActivity
import com.example.myapplication.baseadapter.Divider
import com.example.myapplication.bean.Girl
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_layout.*
import kotlinx.android.synthetic.main.recyclerview_layout.*


class MainActivity : BaseMvpActivity<MainPersenter>(), MainContact.View {


    /*适配器*/
    lateinit var adapter: MainAdapter
    /*加载更多页码*/
    var page = 1

    override fun initPersenter() = MainPersenter(this)

    override fun getLayout() = R.layout.activity_main

    override fun initListener() {
        //添加列表布局
        LayoutInflater.from(this).inflate(R.layout.recyclerview_layout, container_layout, true)
        /*创建adapter*/
        adapter = MainAdapter(this, null)
        /*设置布局样式*/
        recyclerView.layoutManager = LinearLayoutManager(this)
        /*设置adapter*/
        recyclerView.adapter = adapter
        /*设置分割线*/
        recyclerView.addItemDecoration(
            Divider.builder().drawable(
                ContextCompat.getDrawable(
                    this,
                    android.R.color.black
                )
            ).build()
        )
        /*设置自动刷新*/
        mSwipeRefreshLayout.autoRefresh()
        /*设置下来刷新*/
        mSwipeRefreshLayout.setOnRefreshListener {
            page = 1
            mPersenter.getData(page)
        }

        /*设置加载更多*/
        mSwipeRefreshLayout.setOnLoadMoreListener {
            mPersenter.getMoreData(page)
        }
        /*错误布局点击，从新请求*/
        error_layout.setOnClickListener {
            page = 1
            mPersenter.getData(page)
        }
    }

    /*沉浸式状态栏*/
    override fun initBar() {
        super.initBar()
        immersionBar {
            titleBar(toolbar)
        }
    }

    /*第一次加载数据*/
    override fun setData(girl: Girl, hasMore: Boolean) {
        page++
        if (hasMore) mSwipeRefreshLayout.finishRefresh() else mSwipeRefreshLayout.finishRefreshWithNoMoreData()
        adapter.setNewData(girl.results)

    }

    /*加载更多数据*/
    override fun setMoreData(girl: Girl, hasMore: Boolean) {
        if (hasMore) {
            page++
            mSwipeRefreshLayout.finishLoadMore()
        } else {
            mSwipeRefreshLayout.finishLoadMoreWithNoMoreData()
        }
        adapter.concatData(girl.results)

    }

    /*没有网显示dialog*/
    override fun showNoNetDialog() {
        super.showNoNetDialog()
        mSwipeRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
    }

    /*显示错误布局*/
    override fun showErrorView() {
        container_layout.visibility = View.GONE
        error_layout.visibility = View.VISIBLE
    }

    /*请求成功，错误布局消失*/
    open override fun dissmassErrorView() {
        container_layout.visibility = View.VISIBLE
        error_layout.visibility = View.GONE
    }

    /*加载更多失败*/
    override fun LoadMoreError() {
        mSwipeRefreshLayout.finishLoadMore(false)
    }


}
