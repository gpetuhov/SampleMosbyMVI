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
) {

    fun builder() = Builder(this)

    // Builder is needed to create a copy of previous state and add changes to it
    // (by State Reducer).
    class Builder(toCopyFrom: MainViewState) {
        private var loadingText1 = toCopyFrom.loadingText1
        private var errorLoadingText1 = toCopyFrom.errorLoadingText1
        private var text1 = toCopyFrom.text1
        private var loadingText2 = toCopyFrom.loadingText2
        private var errorLoadingText2 = toCopyFrom.errorLoadingText2
        private var text2 = toCopyFrom.text2

        fun firstTextLoading(value: Boolean): Builder {
            loadingText1 = value
            return this
        }

        fun firstTextLoadingError(value: Boolean): Builder {
            errorLoadingText1 = value
            return this
        }

        fun firstText(text: String): Builder {
            text1 = text
            return this
        }

        fun secondTextLoading(value: Boolean): Builder {
            loadingText2 = value
            return this
        }

        fun secondTextLoadingError(value: Boolean): Builder {
            errorLoadingText2 = value
            return this
        }

        fun secondText(text: String): Builder {
            text2 = text
            return this
        }

        fun build() = MainViewState(loadingText1, errorLoadingText1, text1, loadingText2, errorLoadingText2, text2)
    }
}