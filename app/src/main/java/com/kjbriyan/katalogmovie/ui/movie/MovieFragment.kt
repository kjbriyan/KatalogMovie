package com.kjbriyan.katalogmovie.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.kjbriyan.katalogmovie.R
import com.kjbriyan.katalogmovie.adapter.PagerMovieAdapter
import com.kjbriyan.katalogmovie.adapter.RvAdapterNow
import com.kjbriyan.katalogmovie.adapter.RvAdapterPopular
import com.kjbriyan.katalogmovie.databinding.FragmentMovieBinding
import com.kjbriyan.katalogmovie.model.DataTop
import com.kjbriyan.katalogmovie.model.ResultsItem
import com.kjbriyan.katalogmovie.model.ResultsItems
import com.kjbriyan.katalogmovie.ui.seemore.SeemoreActivity
import com.kjbriyan.katalogmovie.util.Helper


class MovieFragment : Fragment(R.layout.fragment_movie), MovieView {
    lateinit var adapterr: RvAdapterPopular
    lateinit var adapterTop : PagerMovieAdapter
    lateinit var adapterNow: RvAdapterNow
    private lateinit var binding: FragmentMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        val presenter = MoviePresenter(this)
        activity.let {
            presenter.getmPopular()
            presenter.getNow()
            presenter.getTop()
        }
        binding.tvSeeAllPopular.setOnClickListener {
            val i = Intent(activity, SeemoreActivity::class.java)
            i.putExtra("show","1")
            startActivity(i)
        }
        binding.tvYouMayLike.setOnClickListener {
            val i = Intent(activity, SeemoreActivity::class.java)
            i.putExtra("show","2")
            startActivity(i)
        }
    }

    companion object {
        fun newIntance(): MovieFragment {
            return MovieFragment()
        }
    }

    override fun onShowLoading() {
       binding.containerShimmerPager.startShimmer()
        binding.containerShimmerPopular.startShimmer()
        binding.containerShimmerTopRated.startShimmer()
    }

    override fun onHideLoadingTop() {
        binding.containerShimmerPager.visibility =
            View.GONE
        binding.containerShimmerPager.stopShimmer()
    }

    override fun onHideLoadingPopular() {
        binding.containerShimmerPopular.visibility =
            View.GONE
        binding.containerShimmerPopular.stopShimmer()
    }

    override fun onHideLoadingNow() {
        binding.containerShimmerTopRated.visibility =
            View.GONE
        binding.containerShimmerTopRated.stopShimmer()

    }

    override fun onDataloadedPopular(results: List<ResultsItem?>?) {
        if (results!!.isNotEmpty()) {
            adapterr = RvAdapterPopular(results)
            activity.let {
                with(binding.rvPopularMovie) {
                    adapter = adapterr
                }
            }
        } else {
            Helper().debuger("null data")
        }
    }

    override fun onDataloadedTop(results: List<ResultsItems?>?) {


        if (results!!.isNotEmpty()) {
            adapterNow = RvAdapterNow(results)
            activity.let {
                with(binding.rvTopRatedMovie) {
                    adapter = adapterNow
                }
            }
        } else {
            Helper().debuger("null data")
        }
    }

    override fun onDataloadedNow(results: List<ResultsItems?>?) {
        if (results!!.isNotEmpty()) {
            adapterTop = PagerMovieAdapter(results)
            activity.let {
                with(binding.viewPagerUpcomingMovie) {
                    adapter = adapterTop
                }
            }
        } else {
            Helper().debuger("null data")
        }
    }


    override fun onDataeror(message: Throwable) {
        Toast.makeText(activity, message.cause.toString(), Toast.LENGTH_SHORT)
    }
}