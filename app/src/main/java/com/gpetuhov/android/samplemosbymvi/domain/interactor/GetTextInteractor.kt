package com.gpetuhov.android.samplemosbymvi.domain.interactor

import com.gpetuhov.android.samplemosbymvi.domain.repository.Repository
import com.gpetuhov.android.samplemosbymvi.domain.viewstate.MainViewState
import io.reactivex.Observable

// In a Production app, this should be injected instead of using singleton.
object GetTextInteractor {

    // TODO: add getText2()

    // TODO: interactor must return partial state

    fun getText1(): Observable<MainViewState> {
        return Repository.loadText1()
            .map<MainViewState> { MainViewState(false, false, it.text, false, false, "") }
            .startWith(MainViewState(true, false, "", false, false, ""))
            .onErrorReturn { MainViewState(false, true, "", false, false, "") }
    }
}