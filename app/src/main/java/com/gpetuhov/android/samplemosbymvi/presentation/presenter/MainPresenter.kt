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

class MainPresenter : MviBasePresenter<MainView, MainViewState>() {

    override fun bindIntents() {

        val firstText: Observable<MainPartialState> = intent(MainView::loadTextIntent1)
            .subscribeOn(Schedulers.io())
            .debounce(400, TimeUnit.MILLISECONDS)
            .switchMap { GetTextInteractor.getText1() }
            .observeOn(AndroidSchedulers.mainThread())

        // TODO: listen to second user intent

        // TODO: merge intent streams into one

        val initialState = MainViewState(false, false, "", false, false, "")

        val stateObservable = firstText.scan(initialState, ::viewStateReducer)

        subscribeViewState(stateObservable, MainView::render)
    }

    private fun viewStateReducer(previousState: MainViewState, changes: MainPartialState): MainViewState {
        // This creates a COPY of previous state
        val previousStateBuilder = previousState.builder()

        // TODO: add second text states

        return when(changes) {
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
        }
    }
}