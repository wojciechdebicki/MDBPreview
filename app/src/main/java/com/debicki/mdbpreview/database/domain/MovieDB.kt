package com.debicki.mdbpreview.database.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDB(
    @PrimaryKey val imdbID: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "poster") val poster: String,
)