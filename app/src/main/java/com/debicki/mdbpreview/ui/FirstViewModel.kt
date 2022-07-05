package com.debicki.mdbpreview.ui

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
    object Progress : State()
    data class Fetched(val movies: List<Movie>) : State()
}

@HiltViewModel
class FirstViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    private val _viewState = MutableLiveData<State>()
    val viewState: LiveData<State> = _viewState

    fun fetch() {
        viewModelScope.launch {
            _viewState.postValue(State.Progress)
            val movies = movieRepository.fetch()

            _viewState.postValue(State.Fetched(movies))
        }
    }

}