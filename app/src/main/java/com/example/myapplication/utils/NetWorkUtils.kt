package NetWorkUtils

import android.content.Context
import android.net.ConnectivityManager
import com.example.myapplication.app.App


/**
 * 判断联网状态时候可用
 */
fun isNetworkReachable(): Boolean {
    var isAvailable = false
    val cm = App.ApplicationINSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val current = cm.activeNetworkInfo ?: return false
    return current.isAvailable
}
