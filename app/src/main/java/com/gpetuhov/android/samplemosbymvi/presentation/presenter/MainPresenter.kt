package com.gpetuhov.android.samplemosbymvi.presentation.presenter

import com.gpetuhov.android.samplemosbymvi.domain.interactor.GetTextInteractor
import com.gpetuhov.android.samplemosbymvi.domain.viewstate.MainPartialState
import com.gpetuhov.android.samplemosbymvi.domain.viewstate.MainViewState
import com.gpetuhov.android.samplemosbymvi.presentation.view.MainView
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

// Presenter listens to all user actions (intents) that come from one view (MainActivity in this case).
// User intents are mapped into interactor calls, which in turn produce streams of partial states.
// All streams of partial states are then merged into one and fed into State Reducer,
// which generates new ViewState based on the previous ViewState and the received partial state.
// New ViewState is generated each time a partial state is emitted in the merged stream of partial states.

class MainPresenter : MviBasePresenter<MainView, MainViewState>() {

    override fun bindIntents() {

        // Subscribe to the first text user intents stream
        // and map it into stream of partial states
        val firstText: Observable<MainPartialState> = intent(MainView::loadTextIntent1)
            .subscribeOn(Schedulers.io())
            .debounce(400, TimeUnit.MILLISECONDS)
            .switchMap { GetTextInteractor.getText1() }
            .observeOn(AndroidSchedulers.mainThread())

        // Subscribe to the second text user intents stream
        // and map it into stream of partial states
        val secondText: Observable<MainPartialState> = intent(MainView::loadTextIntent2)
            .subscribeOn(Schedulers.io())
            .debounce(400, TimeUnit.MILLISECONDS)
            .switchMap { GetTextInteractor.getText2() }
            .observeOn(AndroidSchedulers.mainThread())

        // Merge partial streams for both texts into one
        val allIntents = Observable.merge(firstText, secondText)

        // This is the initial ViewState (loading indicators are not showing, texts are empty)
        val initialState = MainViewState(false, false, "", false, false, "")

        // Convert merged partial states stream into stream of ViewStates.
        // RxJava has scan() operator which fits perfectly into place.
        // Each time partial state is emitted, it is fed into State Reducer,
        // which generates new ViewState based on the previous state and partial state received.
        val stateObservable = allIntents.scan(initialState, ::viewStateReducer)

        // Subscribe View to the stream of ViewStates
        subscribeViewState(stateObservable, MainView::render)
    }

    // This is State Reducer
    private fun viewStateReducer(previousState: MainViewState, changes: MainPartialState): MainViewState {

        // This creates a COPY of previous state
        val previousStateBuilder = previousState.builder()

        return when(changes) {
            // Change a copy of previous state depending on the received partial state

            // Note that for the first text partial states second text values remain unchanged
            // and vice versa (for the second text partial states first text values remain unchanged).

            is MainPartialState.FirstTextLoading -> {
                previousStateBuilder
                    .firstTextLoading(true)
                    .firstTextLoadingError(false)
                    .firstText("")
                    .build()
            }

            is MainPartialState.FirstTextLoaded -> {
                previousStateBuilder
                    .firstTextLoading(false)
                    .firstTextLoadingError(false)
                    .firstText(changes.text)
                    .build()
            }

            is MainPartialState.FirstTextError -> {
                previousStateBuilder
                    .firstTextLoading(false)
                    .firstTextLoadingError(true)
                    .firstText("")
                    .build()
            }

            is MainPartialState.SecondTextLoading -> {
                previousStateBuilder
                    .secondTextLoading(true)
                    .secondTextLoadingError(false)
                    .secondText("")
                    .build()
            }

            is MainPartialState.SecondTextLoaded -> {
                previousStateBuilder
                    .secondTextLoading(false)
                    .secondTextLoadingError(false)
                    .secondText(changes.text)
                    .build()
            }

            is MainPartialState.SecondTextError -> {
                previousStateBuilder
                    .secondTextLoading(false)
                    .secondTextLoadingError(true)
                    .secondText("")
                    .build()
            }
        }
    }
}