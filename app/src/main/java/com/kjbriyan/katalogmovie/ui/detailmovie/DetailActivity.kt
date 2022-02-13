package com.kjbriyan.katalogmovie.ui.detailmovie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kjbriyan.katalogmovie.R
import com.kjbriyan.katalogmovie.adapter.RvReviewAdapters
import com.kjbriyan.katalogmovie.databinding.ActivityDetailBinding
import com.kjbriyan.katalogmovie.model.*
import com.kjbriyan.katalogmovie.network.InitRetrofit
import com.kjbriyan.katalogmovie.util.Helper
import java.util.*
import kotlin.collections.ArrayList

class DetailActivity : AppCompatActivity(), DetailView {
    private lateinit var binding: ActivityDetailBinding
    lateinit var adapters: RvReviewAdapters
    private var page = 1
    private var totalPage: Int = 1
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        layoutManager = LinearLayoutManager(this)
        setupRecyclerView()
        layoutManager = LinearLayoutManager(this)
        val id = intent.extras?.getString("id")
        Helper().debuger("id" + id)
        val presenter = DetailPresenter(this)
        presenter.getDetail(id)
        presenter.getReview(id,page)
        presenter.getTriler(id)

        binding.rvReviews.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                Log.d("MainActivity", "onScrollChange: ")
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstVisibleItemPosition()
                val total  = adapters.itemCount
                if ( page <= totalPage){
                    if (visibleItemCount + pastVisibleItem>= total){
                        page++
                        presenter.getNext(id.toString(),page)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun onShowLoading() {
        binding.containerShimmerPager.visibility = View.VISIBLE
        binding.pbDetail.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        binding.containerShimmerPager.visibility = View.GONE
    }

    override fun onHideLoadingReview() {
        binding.pbDetail.visibility = View.GONE
    }

    override fun onDataloaded(results: ResponseDetailMovie?) {
        Glide.with(this)
            .load(InitRetrofit().IMAGE + results?.backdropPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_movie_nav)
            .into(findViewById(R.id.imgPosterPath))
        binding.tvTitleMovie.text = results?.title
        binding.tvDescStoryLine.text = results?.overview
        val genre = results?.genres?.joinToString(" ") {
            it?.name.toString()
        }
        val originalTitle = results?.originalLanguage?.uppercase(Locale.getDefault())
        val hours = results?.runtime?.div(60)
        val minute = results?.runtime?.rem(60)
        val duration = "${hours}h${minute}m"
        val subTitle = String.format(
            "%s | %s | %s",
            originalTitle, genre, duration
        )
        binding.tvGenreMovie.text = subTitle
    }

    override fun onDataTrailer(results: List<DataTrailer?>?) {

        binding.btnTrailer.root.setOnClickListener {
            intentVideos(results?.get(0)?.key)
        }
    }

    override fun onDataReviewload(results:ResponseReviewMovie?) {
        adapters.addList(results?.results as ArrayList<ResultsItemReview>)
        totalPage = results.totalPages!!
        if (results?.results?.size != 0) {

        } else {
            Toast.makeText(this, "Tidak ada Review", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDataeror(message: Throwable) {
        Helper().debuger(message.cause.toString())
    }

    private fun intentVideos(key: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.youtube.com/watch?v=" + key)
        intent.setPackage("com.google.android.youtube")
        startActivity(intent)
    }
    private fun setupRecyclerView() {
        binding.rvReviews.setHasFixedSize(true)
        binding.rvReviews.layoutManager = layoutManager
        adapters = RvReviewAdapters()
        binding.rvReviews.adapter = adapters
    }
}