package com.debicki.mdbpreview.di

import android.content.Context
import androidx.room.Room
import com.debicki.mdbpreview.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoritesMovieDao(database: AppDatabase) =
        database.favoritesMovieDao()

    @Singleton
    @Provides
    fun provideMovieDao(database: AppDatabase) =
        database.movieDao()

    @Singleton
    @Provides
    fun provideNotInterestedMovieDao(database: AppDatabase) =
        database.notInterestedDao()
}