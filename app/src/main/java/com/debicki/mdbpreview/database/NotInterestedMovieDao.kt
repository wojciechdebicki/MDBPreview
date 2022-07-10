package com.debicki.mdbpreview.database

import androidx.room.*
import com.debicki.mdbpreview.database.domain.NotInterestedMovieDB

@Dao
interface NotInterestedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: NotInterestedMovieDB)

    @Delete
    suspend fun delete(movieDB: NotInterestedMovieDB)

    @Query("DELETE FROM NotInterestedMovieDB")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM NotInterestedMovieDB WHERE imdbID=:imdbID")
    suspend fun count(imdbID: String): Int
}