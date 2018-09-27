package com.ahmaddudayef.footballclub.ui.favorite


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : BaseFragment(), FavoriteMvpView {

    @Inject
    lateinit var presenter: FavoriteMvpPresenter<FavoriteMvpView>

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
