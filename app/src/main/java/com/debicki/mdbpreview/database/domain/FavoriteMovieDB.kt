package com.debicki.mdbpreview.database.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovieDB(
    @PrimaryKey val imdbID: String,
)