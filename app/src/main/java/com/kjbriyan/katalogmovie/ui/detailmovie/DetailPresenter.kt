package com.kjbriyan.katalogmovie.ui.detailmovie

import android.os.Handler
import android.util.Log
import com.kjbriyan.katalogmovie.model.ResponseDetailMovie
import com.kjbriyan.katalogmovie.model.ResponseReviewMovie
import com.kjbriyan.katalogmovie.model.ResponseTrailerMovie
import com.kjbriyan.katalogmovie.network.InitRetrofit
import com.kjbriyan.katalogmovie.util.Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val view: DetailView) {
    fun getDetail(id: String?){
        view.onShowLoading()
        if (id != null) {
            InitRetrofit().getInstance().getDetail(id).enqueue(object : Callback<ResponseDetailMovie> {
                override fun onResponse(
                    call: Call<ResponseDetailMovie>,
                    response: Response<ResponseDetailMovie>
                ) {
                    view.onDataloaded(response.body())
                    view.onHideLoading()
                }

                override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                    view.onHideLoading()
                    view.onDataeror(t)
                }
            })
        }else{
            Helper().debuger("getDetail id is null")
            view.onHideLoading()
        }
    }
    fun getTriler(id : String?){
        view.onShowLoading()
        if (id != null) {
            InitRetrofit().getInstance().getTrailer(id).enqueue(object : Callback<ResponseTrailerMovie> {
                override fun onResponse(
                    call: Call<ResponseTrailerMovie>,
                    response: Response<ResponseTrailerMovie>
                ) {
                    view.onDataTrailer(response.body()?.results)
                    view.onHideLoading()
                }

                override fun onFailure(call: Call<ResponseTrailerMovie>, t: Throwable) {
                    view.onHideLoading()
                    view.onDataeror(t)
                }
            })
        }else{
            Helper().debuger("getTriler id is null")
            view.onHideLoading()
        }
    }
    fun getReview(id : String?, page : Int){
        view.onShowLoading()
        val parameters = HashMap<String, String>()
        parameters["page"] = page.toString()
        Log.d("PAGEE", "$page")
        if (id != null) {
            InitRetrofit().getInstance().getReviews(id,page).enqueue(object : Callback<ResponseReviewMovie> {
                override fun onResponse(
                    call: Call<ResponseReviewMovie>,
                    response: Response<ResponseReviewMovie>
                ) {
                    view.onDataReviewload(response.body())
                    view.onHideLoadingReview()
                }

                override fun onFailure(call: Call<ResponseReviewMovie>, t: Throwable) {
                    view.onHideLoadingReview()
                    view.onDataeror(t)
                }
            })
        }else{
            Helper().debuger("getReview id is null")
            view.onHideLoadingReview()
        }
    }
    fun getNext(id: String?,page: Int) {
        Handler().postDelayed({
            val parameters = HashMap<String, String>()
            parameters["page"] = page.toString()
            Log.d("PAGEE", "$page")
            getReview(id,page)
        }, 3000)
    }
}