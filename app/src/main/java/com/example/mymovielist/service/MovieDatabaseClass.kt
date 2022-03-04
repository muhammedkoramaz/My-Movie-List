package com.example.mymovielist.service

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieDatabaseClass(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "image_url")
    var image_url: String,
) {
    @PrimaryKey(autoGenerate = true)
    var dataId: Int = 0
}
