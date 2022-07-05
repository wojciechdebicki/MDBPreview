package com.debicki.mdbpreview.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    private val _viewState = MutableLiveData<State>(State.Init)
    val viewState: LiveData<State> = _viewState

    fun onSearch(text: String) {
        viewModelScope.launch {
            _viewState.postValue(State.Progress)
            val movies = movieRepository.fetch(text)

            _viewState.postValue(State.Fetched(movies))
        }
    }

}