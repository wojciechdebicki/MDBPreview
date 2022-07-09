package com.debicki.mdbpreview.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debicki.mdbpreview.database.FavoriteMoviesRepository
import com.debicki.mdbpreview.database.MovieDatabaseRepository
import com.debicki.mdbpreview.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    object Progress : State()
    data class Fetched(val movie: Movie, val isFavorite: Boolean) : State()
}

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesMovieDao: FavoriteMoviesRepository,
    private val moviesRepository: MovieDatabaseRepository
) : ViewModel() {
    private val _viewState = MutableLiveData<State>(State.Init)
    val viewState: LiveData<State> = _viewState

    private lateinit var movie: Movie

    fun fetchData(movieId: String) {
        viewModelScope.launch {
            _viewState.postValue(State.Progress)

            movie = moviesRepository.getMovie(movieId)
            val isFavorite = favoritesMovieDao.isFavorite(movie.imdbID)

            _viewState.postValue(State.Fetched(movie, isFavorite))
        }
    }

    fun toggle() {
        viewModelScope.launch {
            favoritesMovieDao.addMovie(movie)
        }
    }
}