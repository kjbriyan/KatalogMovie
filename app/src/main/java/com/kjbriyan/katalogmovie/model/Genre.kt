package com.kjbriyan.katalogmovie.model

data class Genre(
    val id: Int,
    val name: String,
    var selected : Boolean = false
)
