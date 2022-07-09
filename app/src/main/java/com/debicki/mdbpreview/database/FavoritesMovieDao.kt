package com.debicki.mdbpreview.database

import androidx.room.*
import com.debicki.mdbpreview.database.domain.FavoriteMovieDB

@Dao
interface FavoritesMovieDao {
    @Query("SELECT * FROM FavoriteMovieDB")
    suspend fun getAll(): List<FavoriteMovieDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: FavoriteMovieDB)

    @Delete
    suspend fun delete(movieDB: FavoriteMovieDB)

    @Query("SELECT COUNT(*) FROM FavoriteMovieDB WHERE imdbID=:imdbID")
    suspend fun count(imdbID: String): Int
}