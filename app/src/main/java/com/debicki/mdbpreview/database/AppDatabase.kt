package com.debicki.mdbpreview.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.debicki.mdbpreview.database.domain.MovieDB

@Database(entities = [MovieDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesMovieDao(): FavoritesMovieDao
}