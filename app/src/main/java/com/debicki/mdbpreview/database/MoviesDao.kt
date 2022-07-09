package com.debicki.mdbpreview.database

import androidx.room.*
import com.debicki.mdbpreview.database.domain.MovieDB

@Dao
interface MoviesDao {
    @Query("SELECT * FROM MovieDB")
    suspend fun getAll(): List<MovieDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDB>)

    @Query("SELECT * FROM MovieDB WHERE imdbID=:imdbId")
    suspend fun fetch(imdbId: String): MovieDB
}