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
import com.kjbriyan.katalogmovie.model.ResultsItem
import com.kjbriyan.katalogmovie.model.ResultsItems
import com.kjbriyan.katalogmovie.network.InitRetrofit
import com.kjbriyan.katalogmovie.ui.detailmovie.DetailActivity

class RvAdapterPopular(var data: List<ResultsItem?>?) :
    RecyclerView.Adapter<RvAdapterPopular.MyHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: ResultsItem?) {
            val iv  = itemView.findViewById<ImageView>(R.id.iv_movie_poster)
            val tv_tittle  = itemView.findViewById<TextView>(R.id.tv_title_movie)
            val tv_rating = itemView.findViewById<TextView>(R.id.tvRatingMovie)

            tv_tittle.text = get?.title.toString()
            tv_rating.text = get?.voteAverage.toString()
            Glide.with(itemView)
                .load(InitRetrofit().IMAGE+get?.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_movie_nav)
                .into(iv)

            iv.setOnClickListener{
                val i = Intent(itemView.context, DetailActivity::class.java)
                i.putExtra("id",get?.id.toString())
                itemView.context.startActivity(i)
            }
        }
    }
}