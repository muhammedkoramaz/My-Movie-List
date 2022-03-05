import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.service.Database
import com.example.mymovielist.service.Movie
import com.example.mymovielist.service.MovieDatabaseClass

class MovieAdapter(
    private val movies: List<Movie>,
    private val context: Context
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    // TODO viewmodele taşınacak
    private val db: Database = Room.databaseBuilder(context, Database::class.java, "data")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { //viewholder sayesinde arayüzdeki oluşturduğum elemanlara ulaşılıyor.
        val tvMovie: TextView? = itemView.findViewById(R.id.tv_movie)
        val ivMovie: ImageView? = itemView.findViewById(R.id.iv_movie)
        val tvMovieUrl: TextView? = itemView.findViewById(R.id.tv_movie_url)
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
        holder.tvMovie?.text = movie.name
        holder.tvMovieUrl?.text = movie.image_url // TODO geçici çözüm olarak yapılan bu işlemi düzelt!
        holder.ivMovie?.let { Glide.with(context).load(movie.image_url).into(it) }
        holder.ivMovie?.setOnClickListener {
            holder.tvMovie?.visibility = View.VISIBLE

            val data = MovieDatabaseClass(
                holder.tvMovie?.text.toString(),
                holder.tvMovieUrl?.text.toString()
            )
            db.dataDao().insertAll(data)
        }
    }
}
