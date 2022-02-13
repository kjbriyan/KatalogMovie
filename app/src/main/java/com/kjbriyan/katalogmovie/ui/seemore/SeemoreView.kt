package com.kjbriyan.katalogmovie.ui.seemore

import com.kjbriyan.katalogmovie.model.ResponseNowMovie
import com.kjbriyan.katalogmovie.model.ResultsItems

interface SeemoreView {
    fun onShowLoading()
    fun onHideLoading()
    fun onDataloaded(results: ResponseNowMovie?)
    fun onDataeror(message: Throwable)
}