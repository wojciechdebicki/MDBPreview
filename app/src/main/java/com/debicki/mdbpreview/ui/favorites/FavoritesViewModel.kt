package com.debicki.mdbpreview.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debicki.mdbpreview.common.SingleLiveEvent
import com.debicki.mdbpreview.domain.FetchFavoriteMoviesUseCase
import com.debicki.mdbpreview.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class FavoriteState {
    object Init : FavoriteState()
    object Progress : FavoriteState()
    data class Fetched(val movies: List<Movie>) : FavoriteState()
    object EmptyList : FavoriteState()
}

sealed class FavoriteEffect {
    data class OpenDetailsPage(val movie: Movie) : FavoriteEffect()
}

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val fetchFavoriteMoviesUseCase: FetchFavoriteMoviesUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<FavoriteState>(FavoriteState.Init)
    val viewState: LiveData<FavoriteState> = _viewState

    private val _effect = SingleLiveEvent<FavoriteEffect>()
    val effect: LiveData<FavoriteEffect> = _effect

    fun fetchData() {
        viewModelScope.launch {
            _viewState.postValue(FavoriteState.Progress)

            val movies = fetchFavoriteMoviesUseCase.execute()

            val state = if (movies.isEmpty()) {
                FavoriteState.EmptyList
            } else {
                FavoriteState.Fetched(movies)
            }

            _viewState.postValue(state)
        }
    }

    fun onMovieClicked(movie: Movie) {
        _effect.postValue(FavoriteEffect.OpenDetailsPage(movie))
    }
}