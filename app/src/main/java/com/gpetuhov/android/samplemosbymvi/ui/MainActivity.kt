package com.gpetuhov.android.samplemosbymvi.ui

import android.os.Bundle
import android.view.View
import com.gpetuhov.android.samplemosbymvi.R
import com.gpetuhov.android.samplemosbymvi.domain.viewstate.MainViewState
import com.gpetuhov.android.samplemosbymvi.presentation.presenter.MainPresenter
import com.gpetuhov.android.samplemosbymvi.presentation.view.MainView
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding3.view.clicks
import com.pawegio.kandroid.toast
import kotlinx.android.synthetic.main.activity_main.*

// Dividing all the logic into layers keeps MainActivity as simple as this.

class MainActivity : MviActivity<MainView, MainPresenter>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun createPresenter() = MainPresenter()

    override fun loadTextIntent1() = loadTextButton1.clicks()

    override fun loadTextIntent2() = loadTextButton2.clicks()

    override fun render(state: MainViewState) {
        // Rendering the state is just updating visibility of the widgets and showing results

        progress1.visibility = if (state.loadingText1) View.VISIBLE else View.GONE
        text1.visibility = if (state.loadingText1) View.GONE else View.VISIBLE
        text1.text = state.text1

        progress2.visibility = if (state.loadingText2) View.VISIBLE else View.GONE
        text2.visibility = if (state.loadingText2) View.GONE else View.VISIBLE
        text2.text = state.text2

        if (state.errorLoadingText1) showErrorToast(1)
        if (state.errorLoadingText2) showErrorToast(2)
    }

    private fun showErrorToast(textNumber: Int) {
        toast("Error loading text $textNumber")
    }
}