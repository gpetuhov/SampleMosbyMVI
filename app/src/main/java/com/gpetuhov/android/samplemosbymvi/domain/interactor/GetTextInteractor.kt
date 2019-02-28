package com.gpetuhov.android.samplemosbymvi.domain.interactor

import com.gpetuhov.android.samplemosbymvi.domain.repository.Repository
import com.gpetuhov.android.samplemosbymvi.domain.viewstate.MainPartialState
import io.reactivex.Observable

// Here we have one interactor to load both text 1 and 2. There may be 2 separate interactors instead.

// In a Production app, this should be injected instead of using singleton.
object GetTextInteractor {

    // TODO: add getText2()

    fun getText1(): Observable<MainPartialState> {
        return Repository.loadText1()
            .map<MainPartialState> { MainPartialState.FirstTextLoaded(it.text) }
            .startWith(MainPartialState.FirstTextLoading)
            .onErrorReturn { MainPartialState.FirstTextError }
    }
}