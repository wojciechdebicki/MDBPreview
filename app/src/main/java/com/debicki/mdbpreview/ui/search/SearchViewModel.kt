package com.debicki.mdbpreview.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debicki.mdbpreview.common.SingleLiveEvent
import com.debicki.mdbpreview.domain.MovieDescription
import com.debicki.mdbpreview.network.MovieNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    object Progress : State()
    data class Fetched(val movies: List<MovieDescription>) : State()
}

sealed class Effect {
    data class OpenDetailsPage(val movie: MovieDescription) : Effect()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieNetworkRepository: MovieNetworkRepository,
) : ViewModel() {
    private val _viewState = MutableLiveData<State>(State.Init)
    val viewState: LiveData<State> = _viewState

    private val _effect = SingleLiveEvent<Effect>()
    val effect: LiveData<Effect> = _effect

    fun onSearch(text: String) {
        viewModelScope.launch {
            _viewState.postValue(State.Progress)
            val movies = movieNetworkRepository.fetch(text)

            _viewState.postValue(State.Fetched(movies))
        }
    }

    fun onMovieClicked(movieDescription: MovieDescription) {
        _effect.postValue(Effect.OpenDetailsPage(movieDescription))
    }
}