package com.ahmaddudayef.footballclub.ui.player


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.ahmaddudayef.footballclub.ui.detailplayer.DetailPlayerActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_player.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PlayerFragment : BaseFragment(), PlayerMvpView, PlayerAdapter.Callback {

    @Inject
    lateinit var presenter: PlayerMvpPresenter<PlayerMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager
    @Inject
    lateinit var playerAdapter: PlayerAdapter

    companion object {
        private const val EXTRA_PARAM = "EXTRA_PARAM"

        fun newInstance(team: Team): PlayerFragment {
            val fragment = PlayerFragment()
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
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        presenter.onAttach(this)
        playerAdapter.setCallback(this)
        return view
    }

    override fun setUp(view: View) {
        val team: Team? = arguments?.getParcelable(EXTRA_PARAM)
        Log.d("data", team?.idTeam)
        presenter.getPlayerList(team?.idTeam!!)

        player_list.layoutManager = gridLayoutManager
        player_list.setHasFixedSize(true)
        player_list.adapter = playerAdapter
    }

    override fun updateListPlayer(listPlayer: MutableList<Player>) {
        player_list.visibility = VISIBLE
        playerAdapter.addItems(listPlayer)
    }

    override fun onTeamClick(player: Player) {
        startActivity<DetailPlayerActivity>("player_detail" to player)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }



}
