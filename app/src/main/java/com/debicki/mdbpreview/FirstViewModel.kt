package com.debicki.mdbpreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            val data = movieRepository.fetch()

            _viewState.postValue(State.Fetched(data.Search))
        }
    }

}