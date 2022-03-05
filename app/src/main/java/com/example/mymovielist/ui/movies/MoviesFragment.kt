package com.example.mymovielist.ui.movies

import MovieAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.R
import com.example.mymovielist.service.Constant
import com.example.mymovielist.service.Movie
import com.example.mymovielist.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesFragment : Fragment() {
    // Servis bağlantıları yapılıyor.
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL) //Servis URL'in sabit kısmı veriliyor.
        .addConverterFactory(GsonConverterFactory.create()) //JSON datası Gson aracılığı ile dönüştürülüyor.
        .build()
    private val api = retrofit.create(RetrofitService::class.java) //retrofit objesi oluşuyor.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        api.getAllMovies().enqueue(object : Callback<List<Movie>> {
            override fun onFailure( //İnternet bağlantısında sorun yaşanırsa bu fonksiyon çalışıyor.
                call: Call<List<Movie>>,
                t: Throwable
            ) {
                Log.d("Not Internet Connection", t.message!!)
            }

            override fun onResponse( // Servise başarıyla bağlanılırsa bu fonksiyon çalışıyor.
                call: Call<List<Movie>>,
                response: Response<List<Movie>>
            ) {
                val recyclerView =
                    view?.findViewById<RecyclerView>(R.id.recyclerview) // recyclerviewi tanıtıyorum.

                if (response.isSuccessful) { // dönen veriler başarıyla geldiyse buraya giriyor
                    recyclerView?.layoutManager =
                        GridLayoutManager( //her satırda iki item görünmesi için gridlayoutmanager kullanıyorum.
                            requireContext(),
                            2,
                            GridLayoutManager.VERTICAL,
                            false
                        )
                    //todo işlem tekrar tekrar yapılıyor düzelt
                    val adapter =
                        MovieAdapter( // Servisteki tüm verilere response.body() ile ulaşıyorum.
                            response.body()!!,
                            requireContext()
                        )
                    recyclerView?.adapter = adapter //adapteri dolduruyorum.
                }
            }
        })
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }
}