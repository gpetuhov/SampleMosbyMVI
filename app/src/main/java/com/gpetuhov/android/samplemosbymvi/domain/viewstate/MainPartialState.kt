package com.gpetuhov.android.samplemosbymvi.domain.viewstate

// Partial state reflects CHANGES in the process of loading text 1 and 2.
// It can be implemented differently, but here Kotlin sealed classes are used.
// Using sealed class ensures that MainPartialState has only those subclasses,
// that are declared here in this file (and so it is convenient to use with when statement)

sealed class MainPartialState {

    object FirstTextLoading : MainPartialState()

    // This one not only represents the state of the loading, but also contains result (text 1)
    data class FirstTextLoaded(val text: String) : MainPartialState()

    object FirstTextError : MainPartialState()

    object SecondTextLoading: MainPartialState()

    // This one also contains result (text 2)
    data class SecondTextLoaded(val text: String) : MainPartialState()

    object SecondTextError : MainPartialState()
}