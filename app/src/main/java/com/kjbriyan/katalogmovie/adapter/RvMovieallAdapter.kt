package com.kjbriyan.katalogmovie.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kjbriyan.katalogmovie.R
import com.kjbriyan.katalogmovie.model.ResponseNowMovie
import com.kjbriyan.katalogmovie.model.ResultsItems
import com.kjbriyan.katalogmovie.network.InitRetrofit
import com.kjbriyan.katalogmovie.ui.detailmovie.DetailActivity

class RvMovieallAdapter(var data: ResponseNowMovie?) :
    RecyclerView.Adapter<RvMovieallAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_all_movie, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.results?.get(position))
    }

    override fun getItemCount(): Int = data?.results?.size ?: 0

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: ResultsItems?) {
            val iv = itemView.findViewById<ImageView>(R.id.imgBannerMovie)
            val tv_tittle = itemView.findViewById<TextView>(R.id.tvTitleMovie)
            val tv_rating = itemView.findViewById<TextView>(R.id.tvRatingMovie)
            val tv_viewers = itemView.findViewById<TextView>(R.id.tvViewersMovie)
            val tv_years = itemView.findViewById<TextView>(R.id.tvYearMovie)
            val tv_genre = itemView.findViewById<TextView>(R.id.tvGenreMovie)


            tv_viewers.text = get?.popularity.toString()
            tv_years.text = get?.releaseDate.toString()
            tv_tittle.text = get?.title.toString()
            tv_rating.text = get?.voteAverage.toString()
//            tv_genre.setText(get?.genreIds)
            Glide.with(itemView)
                .load(InitRetrofit().IMAGE + get?.backdropPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_movie_nav)
                .into(iv)

            iv.setOnClickListener {
                val i = Intent(itemView.context, DetailActivity::class.java)
                i.putExtra("id", get?.id.toString())
                itemView.context.startActivity(i)
            }
        }
    }

    fun addList(items: List<ResultsItems?>?) {
        if (items != null) {
            data?.results?.toMutableList()?.addAll(items)
        }
        notifyDataSetChanged()
    }

}