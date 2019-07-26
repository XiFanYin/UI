package com.example.myapplication.base

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.dialog.AlertDialog
import com.example.myapplication.utils.NetworkReceiver

/**
 * Created by Administrator on 2018/5/25.
 */
abstract class BaseMvpActivity<P : BasePresenter> : BaseActivity(), BaseView {
    private var dialog: AlertDialog? = null
    private var netBroadcastReceiver: NetworkReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //注册广播,为了dialog消失
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = NetworkReceiver({ hasNet ->
                if (hasNet && dialog != null) dialog?.dismiss()
            })
            val filter = IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netBroadcastReceiver, filter)

        }

    }




    val mPersenter: P by lazy { initPersenter() }


    protected abstract fun initPersenter(): P


    override fun showErrorView() {

    }


    open override fun dissmassErrorView() {

    }


    open override fun showLoading() {

    }

    open override fun dissmassLoading() {

    }

    /*显示没网的dialog*/
    override fun showNoNetDialog() {
        if (dialog == null) {
            val display = windowManager.defaultDisplay
            val width = display.width
            dialog = AlertDialog.Builder(this)
                .setContentView(R.layout.nonetdialog)
                .setBackgroundTransparence(0F)
                .setNoFouse(true)
                .setWidthAndHeight(width - 100, ViewGroup.LayoutParams.WRAP_CONTENT)
                .fromTop(0, 150)
                .show()
            dialog?.setOnClickListener(R.id.dialog_root, { view ->
                dismissNoNetDialog()
                startActivity(Intent(android.provider.Settings.ACTION_WIFI_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            })
        }
    }

    /*让dialog消失*/
    override fun dismissNoNetDialog() {
        dialog?.dismiss()
        dialog = null
    }


    override fun onDestroy() {
        //在presenter中解绑释放view
        mPersenter.detach()
        //注销广播
        if (netBroadcastReceiver != null)
            unregisterReceiver(netBroadcastReceiver);
        super.onDestroy()
    }
}