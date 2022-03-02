package com.example.mymovielist

import MovieAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.service.Constant.BASE_URL
import com.example.mymovielist.service.Movie
import com.example.mymovielist.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) //Servis URL'in sabit kısmı veriliyor.
        .addConverterFactory(GsonConverterFactory.create()) //JSON datası Gson aracılığı ile dönüştürülüyor.
        .build()
    private val api = retrofit.create(RetrofitService::class.java) //retrofit objesi oluşuyor.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api.getAllMovies().enqueue(object: Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) { //İnternet bağlantısında sorun yaşanırsa bu fonksiyon çalışıyor.
                Log.d("Not Internet Connection",t.message!!)
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) { // Servise başarıyla bağlanılırsa bu fonksiyon çalışıyor.
                val recyclerView=findViewById<RecyclerView>(R.id.recyclerview) // recyclerviewi tanıtıyorum.
                //recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false) //her satırda iki item görünmesi için gridlayoutmanager kullanıyorum.

                val adapter =MovieAdapter(response.body()!!,this@MainActivity) // Servisteki tüm verilere response.body() ile ulaşıyorum.
                recyclerView.adapter=adapter //adapteri dolduruyorum.
            }
        })

    }
}