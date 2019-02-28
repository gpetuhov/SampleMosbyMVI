package com.gpetuhov.android.samplemosbymvi.presentation.view

import com.gpetuhov.android.samplemosbymvi.domain.viewstate.MainViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

// This interface is needed for the other layers to be able to communicate with the View
// (get user intents and render state).

interface MainView : MvpView {

    fun loadTextIntent1(): Observable<Unit>

    fun loadTextIntent2(): Observable<Unit>

    fun render(state: MainViewState)
}