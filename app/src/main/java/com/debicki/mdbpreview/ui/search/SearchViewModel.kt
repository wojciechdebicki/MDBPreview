package com.debicki.mdbpreview.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debicki.mdbpreview.common.SingleLiveEvent
import com.debicki.mdbpreview.domain.MovieDescription
import com.debicki.mdbpreview.domain.SearchMovieUseCase
import com.debicki.mdbpreview.network.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    object Progress : State()
    data class Error(val error: String) : State()
    data class Fetched(val movies: List<MovieDescription>) : State()
}

sealed class Effect {
    data class OpenDetailsPage(val movie: MovieDescription) : Effect()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<State>(State.Init)
    val viewState: LiveData<State> = _viewState

    private val _effect = SingleLiveEvent<Effect>()
    val effect: LiveData<Effect> = _effect

    fun onSearch(text: String) {
        viewModelScope.launch {
            _viewState.postValue(State.Progress)

            val state = when (val response = searchMovieUseCase.execute(text)) {
                is Response.Error -> State.Error(response.error)
                is Response.Success -> State.Fetched(response.value)
            }

            _viewState.postValue(state)
        }
    }

    fun onMovieClicked(movieDescription: MovieDescription) {
        _effect.postValue(Effect.OpenDetailsPage(movieDescription))
    }
}