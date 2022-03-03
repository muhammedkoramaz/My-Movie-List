import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.service.Movie
import com.example.mymovielist.ui.movies.MoviesFragment

internal class MovieAdapter(val movies: List<Movie>, val activity: MoviesFragment) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { //viewholder sayesinde arayüzdeki oluşturduğum elemanlara ulaşılıyor.
        val tv_movie = itemView.findViewById<TextView>(R.id.tv_movie)
        val iv_movie = itemView.findViewById<ImageView>(R.id.iv_movie)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder { // Adapter oluşturulduğunda ViewHolder çağırılıyor.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int { // Listedeki eleman sayısını dönderiyor.
        return movies.size
    }

    override fun onBindViewHolder( // Dönen veriler adaptera bağlanılıyor.
        holder: ViewHolder,
        position: Int
    ) {
        val movie: Movie = movies[position]
        holder.tv_movie.text = movie.name
        Glide.with(activity).load(movie.image_url).into(holder.iv_movie)
        holder.iv_movie.setOnClickListener {
            holder.tv_movie.visibility = View.VISIBLE
        }
    }
}
