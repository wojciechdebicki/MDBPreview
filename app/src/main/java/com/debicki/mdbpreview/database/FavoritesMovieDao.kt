package com.debicki.mdbpreview.database

import androidx.room.*
import com.debicki.mdbpreview.database.domain.MovieDB

@Dao
interface FavoritesMovieDao {
    @Query("SELECT * FROM MovieDB")
    suspend fun getAll(): List<MovieDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDB)

    @Delete
    suspend fun delete(movieDB: MovieDB)
}