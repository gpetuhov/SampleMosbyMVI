package com.gpetuhov.android.samplemosbymvi.domain.repository

import com.gpetuhov.android.samplemosbymvi.domain.model.Text
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

// In a Production app, inject your Repository into your use case (interactor) instead of using singleton
object Repository {

    fun loadText1(): Observable<Text> {
        return Observable
            .just(Text(getRandomText()))
            .delay(5, TimeUnit.SECONDS)     // mock database or network latency
    }

    fun loadText2(): Observable<Text> {
        return Observable
            .just(Text(getRandomText()))
            .delay(5, TimeUnit.SECONDS)     // mock database or network latency
    }

    private fun getRandomText(): String {
        val messages = listOf("Bear", "Wolf", "Chicken", "Fox", "Snake", "Duck", "Frog", "Hedgehog", "Horse")
        return messages[Random().nextInt(messages.size)]
    }
}