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
import com.kjbriyan.katalogmovie.model.ResultsItems
import com.kjbriyan.katalogmovie.network.InitRetrofit
import com.kjbriyan.katalogmovie.ui.detailmovie.DetailActivity

class RvAdapter : RecyclerView.Adapter<RvAdapter.UsersViewHolder>(){

    private var list = ArrayList<ResultsItems>()

    inner class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(get: ResultsItems?) {
            val iv = itemView.findViewById<ImageView>(R.id.imgBannerMovie)
            val tv_tittle = itemView.findViewById<TextView>(R.id.tvTitleMovie)
            val tv_rating = itemView.findViewById<TextView>(R.id.tvRatingMovie)
            val tv_viewers = itemView.findViewById<TextView>(R.id.tvViewersMovie)
            val tv_years = itemView.findViewById<TextView>(R.id.tvYearMovie)
            val tv_genre = itemView.findViewById<TextView>(R.id.tvGenreMovie)


            tv_viewers.setText(get?.popularity.toString())
            tv_years.setText(get?.releaseDate.toString())
            tv_tittle.setText(get?.title.toString())
            tv_rating.setText(get?.voteAverage.toString())
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_all_movie, parent, false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addList(items: ArrayList<ResultsItems>){
        list.addAll(items)
        notifyDataSetChanged()
    }

}