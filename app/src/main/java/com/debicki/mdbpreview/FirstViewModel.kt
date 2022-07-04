package com.debicki.mdbpreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class State {
    data class Init(val value: Int) : State()
}

@HiltViewModel
class FirstViewModel @Inject constructor(private val dependencyTester: DependencyTester) : ViewModel() {

    private val _viewState = MutableLiveData<State>()
    val viewState: LiveData<State> = _viewState

    fun testMe() {
        _viewState.postValue(State.Init(dependencyTester.testValue()))
    }

}