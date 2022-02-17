package com.kjbriyan.katalogmovie.ui.movie

import com.kjbriyan.katalogmovie.model.DataTop
import com.kjbriyan.katalogmovie.model.ResultsItem
import com.kjbriyan.katalogmovie.model.ResultsItems

interface MovieView {
    fun onShowLoading()
    fun onHideLoadingTop()
    fun onHideLoadingPopular()
    fun onHideLoadingNow()
    fun onDataloadedPopular(results : List<ResultsItem?>?)
    fun onDataloadedTop(results : List<ResultsItems?>?)
    fun onDataloadedNow(results : List<ResultsItems?>?)
    fun onDataeror (message : Throwable)

}