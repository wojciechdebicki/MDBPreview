package com.debicki.mdbpreview.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debicki.mdbpreview.database.FavoriteMoviesRepository
import com.debicki.mdbpreview.database.MovieDatabaseRepository
import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.network.MovieNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    object Progress : State()
    data class Fetched(val movie: Movie, val isFavorite: Boolean) : State()
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteMoviesRepository: FavoriteMoviesRepository,
    private val movieDatabaseRepository: MovieDatabaseRepository,
    private val movieNetworkRepository: MovieNetworkRepository
) : ViewModel() {
    private val _viewState = MutableLiveData<State>(State.Init)
    val viewState: LiveData<State> = _viewState

    private lateinit var movie: Movie

    fun fetchData(movieId: String) {
        viewModelScope.launch {
            _viewState.postValue(State.Progress)

            val tempMovie = movieDatabaseRepository.getMovie(movieId)
            if (tempMovie == null) {
                movie = movieNetworkRepository.getDetails(movieId)
                movieDatabaseRepository.add(movie)
            } else {
                movie = tempMovie
            }

            val isFavorite = favoriteMoviesRepository.isFavorite(movie.imdbID)

            _viewState.postValue(State.Fetched(movie, isFavorite))
        }
    }

    fun favoriteStateChanged(checked: Boolean) {
        viewModelScope.launch {
            if (checked) {
                favoriteMoviesRepository.addMovie(movie)
            } else {
                favoriteMoviesRepository.remove(movie)
            }
        }
    }
}