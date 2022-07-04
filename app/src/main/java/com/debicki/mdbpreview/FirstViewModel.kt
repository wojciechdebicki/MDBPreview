package com.debicki.mdbpreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class State {
    object Init : State()
}

class FirstViewModel : ViewModel() {

    private val _viewState = MutableLiveData<State>()
    val viewState: LiveData<State> = _viewState

    fun testMe() {
        _viewState.postValue(State.Init)
    }

}