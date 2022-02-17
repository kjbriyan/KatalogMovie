package com.kjbriyan.katalogmovie.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kjbriyan.katalogmovie.R
import com.kjbriyan.katalogmovie.model.ResultsItemReview

class RvReviewAdapters : RecyclerView.Adapter<RvReviewAdapters.UsersViewHolder>(){

    private var list = ArrayList<ResultsItemReview>()

    inner class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(get: ResultsItemReview?) {
            val iv = itemView.findViewById<ImageView>(R.id.circularImageView)
            val tvUname = itemView.findViewById<TextView>(R.id.tvUname)
            val tvReview = itemView.findViewById<TextView>(R.id.tvReviews)

            tvUname.text = get?.author.toString()
            tvReview.text = get?.content.toString()
            val img = get?.authorDetails?.avatarPath?.substring(1)
            val imghttp = "https://secure.gravatar.com/avatar/"
            var urlimg = ""
            if (img?.substring(0,4).toString() != "http"){
                urlimg = imghttp+img
            }else{
                urlimg = img.toString()
            }
            Glide.with(itemView)
                .load(urlimg)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_movie_nav)
                .into(iv)
            Log.d("reviewnya",urlimg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_review, parent, false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addList(items: ArrayList<ResultsItemReview>){
        list.addAll(items)
        notifyDataSetChanged()
    }
}