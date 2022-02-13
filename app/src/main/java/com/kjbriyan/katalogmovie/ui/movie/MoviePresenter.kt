package com.kjbriyan.katalogmovie.ui.movie

import com.kjbriyan.katalogmovie.model.ResponseNowMovie
import com.kjbriyan.katalogmovie.model.ResponsePopularMovie
import com.kjbriyan.katalogmovie.model.ResponseTopMovie
import com.kjbriyan.katalogmovie.network.InitRetrofit
import com.kjbriyan.katalogmovie.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val view: MovieView) {
    fun getmPopular(){
        view.onShowLoading()

        InitRetrofit().getInstance().getPopular().enqueue(object : Callback<ResponsePopularMovie> {
            override fun onResponse(
                call: Call<ResponsePopularMovie>,
                response: Response<ResponsePopularMovie>
            ) {
                Helper().debuger(response.body().toString())
                view.onDataloadedPopular(response.body()?.results)
                view.onHideLoadingPopular()
            }

            override fun onFailure(call: Call<ResponsePopularMovie>, t: Throwable) {
                view.onHideLoadingPopular()
                view.onDataeror(t)
            }
        })
    }

    fun getNow(){
        view.onShowLoading()
        InitRetrofit().getInstance().getNowplay().enqueue(object : Callback<ResponseNowMovie> {
            override fun onResponse(
                call: Call<ResponseNowMovie>,
                response: Response<ResponseNowMovie>
            ) {
                Helper().debuger(response.body().toString())
                view.onDataloadedNow(response.body()?.results)
                view.onHideLoadingNow()
            }

            override fun onFailure(call: Call<ResponseNowMovie>, t: Throwable) {
                view.onHideLoadingNow()
                view.onDataeror(t)
            }
        })
    }

    fun getTop(){
        view.onShowLoading()
        InitRetrofit().getInstance().getTop().enqueue(object : Callback<ResponseTopMovie> {
            override fun onResponse(
                call: Call<ResponseTopMovie>,
                response: Response<ResponseTopMovie>
            ) {
                Helper().debuger(response.body().toString())
                view.onDataloadedTop(response.body()?.results)
                view.onHideLoadingTop()
            }

            override fun onFailure(call: Call<ResponseTopMovie>, t: Throwable) {
                view.onHideLoadingTop()
                view.onDataeror(t)
            }
        })
    }
}