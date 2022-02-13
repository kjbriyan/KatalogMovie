package com.kjbriyan.katalogmovie.ui.Genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kjbriyan.katalogmovie.R



class GenreFragment : Fragment(R.layout.fragment_genre) {
    // TODO: Rename and change types of parameters

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        fun newIntance(): GenreFragment {
            return GenreFragment()
        }
    }
}