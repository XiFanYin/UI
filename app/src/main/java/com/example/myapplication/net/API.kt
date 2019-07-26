package xifuyin.tumour.com.a51ehealth.kotlin_simple.net.api

import com.example.myapplication.bean.Girl
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path



/**
 * Created by Administrator on 2018/5/22.
 */
interface API {

    @GET("10/{page}")
    fun getItem(@Path("page") page: Int): Observable<Girl>


}

