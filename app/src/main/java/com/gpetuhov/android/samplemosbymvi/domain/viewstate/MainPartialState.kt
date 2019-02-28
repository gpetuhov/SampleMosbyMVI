package com.gpetuhov.android.samplemosbymvi.domain.viewstate

sealed class MainPartialState {
    object FirstTextLoading : MainPartialState()
    data class FirstTextLoaded(val text: String) : MainPartialState()
    object FirstTextError : MainPartialState()
    object SecondTextLoading: MainPartialState()
    data class SecondTextLoaded(val text: String) : MainPartialState()
    object SecondTextError : MainPartialState()
}