package com.example.mymovielist.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao { // Model ile sqlite arasındaki veri aktarımı yapılacak yer.
    @Query("SELECT * FROM movie")
    fun getAllFavoriteMovies(): List<MovieDatabaseClass>

    @Insert
    fun insertAll(vararg data: MovieDatabaseClass)
}