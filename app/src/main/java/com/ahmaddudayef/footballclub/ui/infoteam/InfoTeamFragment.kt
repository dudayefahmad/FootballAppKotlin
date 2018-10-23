package com.ahmaddudayef.footballclub.ui.infoteam


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.R.id.teams
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_info_team.*
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.bundleOf
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class InfoTeamFragment : BaseFragment(), InfoTeamMvpView {

    @Inject
    lateinit var presenter: InfoTeamMvpPresenter<InfoTeamMvpView>

    companion object {
        private const val EXTRA_PARAM = "EXTRA_PARAM"

        fun newInstance(team: Team): InfoTeamFragment {
            val fragment = InfoTeamFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_PARAM, team)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info_team, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        val team: Team? = arguments?.getParcelable(EXTRA_PARAM)
        Glide.with(this)
                .load(team?.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_hourglass_empty_black_24dp))
                .into(imgBadge)

        teamName.text = team?.strTeam
        tvManager.text = team?.strManager
        tvStadium.text = team?.strStadium
        teamOverview.text = team?.strDescriptionEN
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
