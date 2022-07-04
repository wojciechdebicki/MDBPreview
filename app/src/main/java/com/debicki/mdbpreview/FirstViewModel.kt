package com.debicki.mdbpreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    data class Fetched(val size: Int) : State()
}

@HiltViewModel
class FirstViewModel @Inject constructor(private val commentRepository: CommentRepository) : ViewModel() {
    private val _viewState = MutableLiveData<State>()
    val viewState: LiveData<State> = _viewState

    fun fetchComments() {
        viewModelScope.launch {
            _viewState.postValue(State.Init)
            val comments = commentRepository.fetchComments()

            _viewState.postValue(State.Fetched(comments.size))
        }
    }

}