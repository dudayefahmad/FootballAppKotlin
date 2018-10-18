package com.ahmaddudayef.footballclub.ui.detailteam

import android.os.Bundle
import android.view.Menu
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.base.BaseActivity
import com.ahmaddudayef.footballclub.ui.infoteam.InfoTeamFragment
import com.ahmaddudayef.footballclub.ui.matches.ViewPagerMatchAdapter
import com.ahmaddudayef.footballclub.ui.player.PlayerFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail_team.*
import javax.inject.Inject

class DetailTeamActivity : BaseActivity(), DetailTeamMvpView {

    @Inject
    lateinit var presenter: DetailTeamMvpPresenter<DetailTeamMvpView>
    lateinit var team: Team
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        team = intent.getParcelableExtra("team_detail")
        presenter.onAttach(this)
        init(team)
    }

    private fun init(team: Team){
        setSupportActionBar(toolbar)
        supportActionBar?.title = team.strTeam
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                .into(imageTeam)

        val adapter = ViewPagerMatchAdapter(supportFragmentManager)
        adapter.populateFragment(InfoTeamFragment(), "Team Info")
        adapter.populateFragment(PlayerFragment(), "Player Info")
        viewpagerTeam.adapter = adapter
        tabsTeam.setupWithViewPager(viewpagerTeam)

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }


}
