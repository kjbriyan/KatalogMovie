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
import com.google.android.material.card.MaterialCardView
import com.kjbriyan.katalogmovie.R
import com.kjbriyan.katalogmovie.model.DataTop
import com.kjbriyan.katalogmovie.model.ResultsItems
import com.kjbriyan.katalogmovie.network.InitRetrofit
import com.kjbriyan.katalogmovie.ui.detailmovie.DetailActivity


class PagerMovieAdapter(var data: List<ResultsItems?>?) :
    RecyclerView.Adapter<PagerMovieAdapter.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(get: ResultsItems?) {
            val iv = itemView.findViewById<ImageView>(R.id.iv_movie_poster)
            val title = itemView.findViewById<TextView>(R.id.tv_title_movie)
            val rating = itemView.findViewById<TextView>(R.id.tv_rating_movie)
            val item  = itemView.findViewById<MaterialCardView>(R.id.item_top)

            title.setText(get?.title.toString())
            rating.setText(get?.voteAverage.toString())
            Glide.with(itemView)
                .load(InitRetrofit().IMAGE+get?.backdropPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_movie_nav)
                .into(iv)

            item.setOnClickListener{
                val i = Intent(itemView.context, DetailActivity::class.java)
                i.putExtra("id",get?.id.toString())
                itemView.context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerMovieAdapter.MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_pager, parent, false)

        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: PagerMovieAdapter.MyHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    override fun getItemCount(): Int = data?.size ?: 0

}