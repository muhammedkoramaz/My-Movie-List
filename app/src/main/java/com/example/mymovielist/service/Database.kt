package com.example.mymovielist.service

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDatabaseClass::class], version = 3)
abstract class Database : RoomDatabase() {
    abstract fun dataDao(): DataDao
}