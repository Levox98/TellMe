package com.tellme.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

abstract class BaseAction {
    override fun equals(other: Any?) = false
    override fun hashCode() = Random.nextInt()
}

abstract class BaseViewModel<State : Any, Action : BaseAction, Event>(initialState: State): ViewModel() {
    private val _viewStates = MutableStateFlow(initialState)
    val viewStates: StateFlow<State> = _viewStates.asStateFlow()

    private val _viewActions = Channel<Action>(Channel.CONFLATED)
    val viewActions = _viewActions.receiveAsFlow()

    protected var viewState: State
        get() = _viewStates.value
        set(value) {
            _viewStates.value = value
        }

    abstract fun obtainEvent(viewEvent: Event)

    protected fun sendAction(action: Action) {
        viewModelScope.launch {
            _viewActions.send(action)
        }
    }
}
