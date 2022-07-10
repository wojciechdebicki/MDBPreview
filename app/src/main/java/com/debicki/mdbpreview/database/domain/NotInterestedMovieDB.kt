package com.debicki.mdbpreview.database.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotInterestedMovieDB(
    @PrimaryKey val imdbID: String,
)