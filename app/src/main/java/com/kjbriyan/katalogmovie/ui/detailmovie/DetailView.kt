package com.kjbriyan.katalogmovie.ui.detailmovie

import com.kjbriyan.katalogmovie.model.DataTrailer
import com.kjbriyan.katalogmovie.model.ResponseDetailMovie
import com.kjbriyan.katalogmovie.model.ResponseReviewMovie
import com.kjbriyan.katalogmovie.model.ResultsItemReview

interface DetailView {
    fun onShowLoading()
    fun onHideLoading()
    fun onHideLoadingReview()
    fun onDataloaded(results : ResponseDetailMovie?)
    fun onDataTrailer(results: List<DataTrailer?>?)
    fun onDataReviewload(results: ResponseReviewMovie?)
    fun onDataeror(message : Throwable)
}