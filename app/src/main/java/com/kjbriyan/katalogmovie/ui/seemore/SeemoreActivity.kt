package com.kjbriyan.katalogmovie.ui.seemore

import RvAdapter
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kjbriyan.katalogmovie.adapter.RvMovieallAdapter
import com.kjbriyan.katalogmovie.databinding.ActivitySeemoreBinding
import com.kjbriyan.katalogmovie.model.ResponseNowMovie
import com.kjbriyan.katalogmovie.model.ResultsItems
import com.kjbriyan.katalogmovie.util.Helper

class SeemoreActivity : AppCompatActivity(), SeemoreView {
    lateinit var binding: ActivitySeemoreBinding
    lateinit var adapter: RvMovieallAdapter
    lateinit var adapters: RvAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var page = 1
    private var totalPage: Int = 1
    lateinit var presenter: SeemorePresenter


    private var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeemoreBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = SeemorePresenter(this)
        val show = intent.extras?.getString("show")
        Helper().debuger("show" + show)
        if (show == "2") {
            presenter.getmPopular(page)
            binding.tvTitleBar.text ="You May like"
        } else {
            presenter.getNow(page)
            binding.tvTitleBar.text ="Popular"
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        layoutManager = LinearLayoutManager(this)

        setupRecyclerView()

        binding.rvAllMovie.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                Log.d("MainActivity", "onScrollChange: ")
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstVisibleItemPosition()
                val total  = adapters.itemCount
                if ( page < totalPage){
                    if (visibleItemCount + pastVisibleItem>= total){
                        page++
                        presenter.getNext(show.toString(),page)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvAllMovie.setHasFixedSize(true)
        binding.rvAllMovie.layoutManager = layoutManager
        adapters = RvAdapter()
        binding.rvAllMovie.adapter = adapters
    }

    override fun onShowLoading() {
        binding.containerShimmer.startShimmer()
        isLoading = true
        binding.pball.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        binding.containerShimmer.stopShimmer()
        binding.containerShimmer.visibility = View.GONE
        isLoading = false
        binding.pball.visibility = View.GONE
    }

    override fun onDataloaded(results: ResponseNowMovie?) {
        adapters.addList(results?.results as ArrayList<ResultsItems>)
        totalPage = results.totalPages!!
    }


    override fun onDataeror(message: Throwable) {
        Log.d("All Movie", message.cause.toString())
    }
}