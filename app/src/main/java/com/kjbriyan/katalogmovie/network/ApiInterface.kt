package com.kjbriyan.katalogmovie.network

import com.kjbriyan.katalogmovie.model.*
import retrofit2.Call
import retrofit2.http.*

public interface ApiInterface {

    @GET("popular")
    fun getPopular(): Call<ResponsePopularMovie>

    @GET("now_playing")
    fun getNowplay(): Call<ResponseNowMovie>

    @GET("top_rated")
    fun getTop(): Call<ResponseTopMovie>

    @GET("{id}")
    fun getDetail(
        @Path("id") id : String
    ): Call<ResponseDetailMovie>

    @GET("{id}/videos")
    fun getTrailer(
        @Path("id") id : String
    ): Call<ResponseTrailerMovie>

    @GET("{id}/reviews")
    fun getReviews(
        @Path("id") id : String,
        @Query("page") page: Int
    ): Call<ResponseReviewMovie>

    @GET("now_playing")
    fun getNowplayall(
        @Query("page") page: Int
    ): Call<ResponseNowMovie>

    @GET("top_rated")
    fun getTopall(
        @Query("page") page: Int
    ): Call<ResponseNowMovie>
}