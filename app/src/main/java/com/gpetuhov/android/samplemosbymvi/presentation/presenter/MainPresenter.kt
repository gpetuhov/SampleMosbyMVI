package com.gpetuhov.android.samplemosbymvi.presentation.presenter

import com.gpetuhov.android.samplemosbymvi.domain.interactor.GetTextInteractor
import com.gpetuhov.android.samplemosbymvi.domain.viewstate.MainViewState
import com.gpetuhov.android.samplemosbymvi.presentation.view.MainView
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainPresenter : MviBasePresenter<MainView, MainViewState>() {

    override fun bindIntents() {

        val mainViewState: Observable<MainViewState> = intent(MainView::loadTextIntent1)
            .subscribeOn(Schedulers.io())
            .debounce(400, TimeUnit.MILLISECONDS)
            .switchMap { GetTextInteractor.getText1() }
            .observeOn(AndroidSchedulers.mainThread())

        // TODO: listen to second user intent

        // TODO: add State Reducer

        subscribeViewState(mainViewState, MainView::render)
    }
}