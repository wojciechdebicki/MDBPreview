package com.debicki.mdbpreview.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debicki.mdbpreview.common.SingleLiveEvent
import com.debicki.mdbpreview.database.MovieDatabaseRepository
import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.network.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    object Progress : State()
    data class Fetched(val movies: List<Movie>) : State()
}

sealed class Effect {
    data class OpenDetailsPage(val movie: Movie) : Effect()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDatabaseRepository: MovieDatabaseRepository
) : ViewModel() {
    private val _viewState = MutableLiveData<State>(State.Init)
    val viewState: LiveData<State> = _viewState

    private val _effect = SingleLiveEvent<Effect>()
    val effect: LiveData<Effect> = _effect

    fun onSearch(text: String) {
        viewModelScope.launch {
            _viewState.postValue(State.Progress)
            val movies = movieRepository.fetch(text)

            movieDatabaseRepository.addAll(movies)

            _viewState.postValue(State.Fetched(movies))
        }
    }

    fun onMovieClicked(movie: Movie) {
        _effect.postValue(Effect.OpenDetailsPage(movie))
    }

}