package com.debicki.mdbpreview.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debicki.mdbpreview.domain.FetchDetailMovieUseCase
import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.domain.ToggleFavoriteUseCase
import com.debicki.mdbpreview.domain.ToggleNotInterestedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    object Progress : State()
    data class Fetched(val movie: Movie, val isFavorite: Boolean, val isBlocked: Boolean) : State()
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchDetailMovieUseCase: FetchDetailMovieUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val toggleNotInterestedUseCase: ToggleNotInterestedUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<State>(State.Init)
    val viewState: LiveData<State> = _viewState

    private lateinit var movie: Movie

    fun fetchData(movieId: String) {
        viewModelScope.launch {
            _viewState.postValue(State.Progress)

            val data = fetchDetailMovieUseCase.execute(movieId)
            movie = data.movie

            _viewState.postValue(State.Fetched(movie, data.isFavorite, data.notInterested))
        }
    }

    fun favoriteStateChanged(checked: Boolean) {
        viewModelScope.launch {
            toggleFavoriteUseCase.execute(checked, movie)
        }
    }

    fun notInterestedStateChanged(checked: Boolean) {
        viewModelScope.launch {
            toggleNotInterestedUseCase.execute(checked, movie)
        }
    }
}