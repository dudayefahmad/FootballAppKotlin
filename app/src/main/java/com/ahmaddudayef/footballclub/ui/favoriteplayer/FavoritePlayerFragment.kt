package com.ahmaddudayef.footballclub.ui.favoriteplayer


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.player.FavoritePlayers
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.data.network.model.player.Players
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favorite_player.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class FavoritePlayerFragment : BaseFragment(), FavoritePlayerMvpView {

    @Inject
    lateinit var presenter: FavoritePlayerMvpPresenter<FavoritePlayerMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    private var list_player: MutableList<Player> = mutableListOf()
    private var adapter = FavoritePlayerAdapter(list_player)

    companion object {
        fun newInstance() = FavoritePlayerFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_player, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        listFavPlayer.layoutManager = gridLayoutManager
        listFavPlayer.adapter = adapter
        swipeRefresh.setOnRefreshListener { getFavoriteFromDb() }
        swipeRefresh.post {
            swipeRefresh.isRefreshing = true
            getFavoriteFromDb()
        }
    }

    override fun showPlayerFavorite(players: FavoritePlayers) {
        swipeRefresh.isRefreshing = false
        list_player.add(players.players!![0])
        adapter.notifyDataSetChanged()
    }

    private fun getFavoriteFromDb() {
        list_player.clear()
        presenter.getPlayer(context)
        swipeRefresh.isRefreshing = false
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
