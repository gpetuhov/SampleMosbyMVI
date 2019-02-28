package com.gpetuhov.android.samplemosbymvi.domain.interactor

import com.gpetuhov.android.samplemosbymvi.domain.repository.Repository
import com.gpetuhov.android.samplemosbymvi.domain.viewstate.MainPartialState
import io.reactivex.Observable

// Here we have one interactor to load both text 1 and 2. There may be 2 separate interactors instead.

// In a Production app, this should be injected instead of using singleton.
object GetTextInteractor {

    // Note that here interactor does NOT return ViewState directly.
    // Instead PartialState is returned, which reflects changes
    // of the loading process of the first or second text.

    fun getText1(): Observable<MainPartialState> {
        return Repository.loadText1()
            .map<MainPartialState> { MainPartialState.FirstTextLoaded(it.text) }
            .startWith(MainPartialState.FirstTextLoading)
            .onErrorReturn { MainPartialState.FirstTextError }
    }

    fun getText2(): Observable<MainPartialState> {
        return Repository.loadText2()
            .map<MainPartialState> { MainPartialState.SecondTextLoaded(it.text) }
            .startWith(MainPartialState.SecondTextLoading)
            .onErrorReturn { MainPartialState.SecondTextError }
    }
}