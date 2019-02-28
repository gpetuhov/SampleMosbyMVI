package com.gpetuhov.android.samplemosbymvi.domain.viewstate

sealed class MainPartialState {
    object FirstTextLoading : MainPartialState()
    data class FirstTextLoaded(val text: String) : MainPartialState()
    object FirstTextError : MainPartialState()

    // TODO: add states for text 2
}