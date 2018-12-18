package com.canonicalexamples.fragmentscenariocfm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a.*

class AFragment : Fragment(), AView {
    // <editor-fold desc="Properties">
    lateinit var presenter: APresenter
    // </editor-fold>

    // <editor-fold desc="Lifecycle">
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? = inflater.inflate(R.layout.fragment_a, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveOrInject()
        setUpFloatingActionButton()
        presenter.viewReady()
    }

    private fun retrieveOrInject() {
        val fragment = childFragmentManager.findFragmentByTag(RetainedFragment.retainedFragmentTag)
            as RetainedFragment?
        if (fragment == null) {
            val serviceLocator = requireActivity().application as ServiceLocator
            presenter = serviceLocator.providePresenter()
            presenter.view = this
            val retainer = RetainedFragment()
            childFragmentManager.beginTransaction()
                .add(retainer, RetainedFragment.retainedFragmentTag)
                .commit()
            retainer.presenter = presenter
        } else {
            presenter = fragment.presenter
            presenter.view = this
        }
    }

    private fun setUpFloatingActionButton() {
        fab.setOnClickListener {
            presenter.buttonTapped()
        }
    }

    // </editor-fold>

    // <editor-fold desc="AView">
    override fun displayCount(count: String) {
        textView.text = count
    }
    // </editor-fold>

    // <editor-fold desc="Headless fragment">
    class RetainedFragment : Fragment() {
        companion object {
            const val retainedFragmentTag = "RetainedFragmentTag"
        }

        lateinit var presenter: APresenter
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }
    }
    // </editor-fold>
}
