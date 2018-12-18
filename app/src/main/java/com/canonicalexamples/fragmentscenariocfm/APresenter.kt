package com.canonicalexamples.fragmentscenariocfm

import com.canonicalexamples.realprogrammers.helper.WeakReferenceHolder
import kotlin.properties.Delegates

class APresenter {
    // <editor-fold desc="Properties">
    var view: AView? by WeakReferenceHolder()
    private var counter : Int by Delegates.observable(initialValue = 0, onChange = { _, _, newValue: Int ->
        view?.displayCount(count = "$newValue taps")
    })
    // </editor-fold>

    // <editor-fold desc="Events">
    fun viewReady() {
        view?.displayCount(count = "$counter taps")
    }

    fun buttonTapped() {
        counter++
    }
    // </editor-fold>
}
