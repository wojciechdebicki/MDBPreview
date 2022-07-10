package com.debicki.mdbpreview.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.debicki.mdbpreview.database.domain.MovieDB

@Dao
interface MoviesDao {
    @Query("SELECT * FROM MovieDB")
    suspend fun getAll(): List<MovieDB>

    @Query("SELECT * FROM MovieDB WHERE imdbID IN (:ids)")
    suspend fun getAll(ids: List<String>): List<MovieDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDB)

    @Query("SELECT * FROM MovieDB WHERE imdbID=:imdbId")
    suspend fun fetch(imdbId: String): MovieDB?
}