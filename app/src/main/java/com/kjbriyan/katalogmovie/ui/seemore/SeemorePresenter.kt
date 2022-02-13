package com.kjbriyan.katalogmovie.ui.seemore

import android.os.Handler
import android.util.Log
import com.kjbriyan.katalogmovie.model.ResponseNowMovie
import com.kjbriyan.katalogmovie.network.InitRetrofit
import com.kjbriyan.katalogmovie.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeemorePresenter(private val view: SeemoreView) {
    fun getmPopular(page: Int) {
        view.onShowLoading()

        InitRetrofit().getInstance().getTopall(page).enqueue(object : Callback<ResponseNowMovie> {
            override fun onResponse(
                call: Call<ResponseNowMovie>,
                response: Response<ResponseNowMovie>
            ) {
                Helper().debuger(response.body().toString())
                view.onDataloaded(response.body())
                view.onHideLoading()
            }

            override fun onFailure(call: Call<ResponseNowMovie>, t: Throwable) {
                view.onHideLoading()
                view.onDataeror(t)
            }
        })
    }

    fun getNow(page: Int) {
        view.onShowLoading()
        InitRetrofit().getInstance().getNowplayall(page)
            .enqueue(object : Callback<ResponseNowMovie> {
                override fun onResponse(
                    call: Call<ResponseNowMovie>,
                    response: Response<ResponseNowMovie>
                ) {
                    Helper().debuger(response.body().toString())
                    view.onDataloaded(response.body())
                    view.onHideLoading()
                }

                override fun onFailure(call: Call<ResponseNowMovie>, t: Throwable) {
                    view.onHideLoading()
                    view.onDataeror(t)
                }
            })
    }

    fun getNext(id:String,page: Int) {
        Handler().postDelayed({
            val parameters = HashMap<String, String>()
            parameters["page"] = page.toString()
            Log.d("PAGEE", "$page")
            if (id == "2"){
                getmPopular(page)
            }else {
                getNow(page)
            }
        }, 3000)
    }
}