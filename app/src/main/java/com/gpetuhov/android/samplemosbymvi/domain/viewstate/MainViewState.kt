package com.gpetuhov.android.samplemosbymvi.domain.viewstate

// In this example we implement view state as Kotlin data class
// which keeps loading state and result for text 1 and 2.

data class MainViewState(
    val loadingText1: Boolean,
    val errorLoadingText1: Boolean,
    val text1: String,
    val loadingText2: Boolean,
    val errorLoadingText2: Boolean,
    val text2: String
)