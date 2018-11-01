package com.ahmaddudayef.footballclub.ui.detailteam

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.TeamEntity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


class DetailTeamActivity : BaseActivity(), DetailTeamMvpView, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
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
        setSupportActionBar(toolbar2)
        toolbar2.title = team.strTeam
        toolbar.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = true
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = team.strTeam
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = " "//carefull there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        })

        if (!team.strTeamFanart1.equals(null)){
            Glide.with(applicationContext)
                    .load(team.strTeamFanart1)
                    .apply(RequestOptions().placeholder(R.drawable.
                            ic_hourglass_empty_black_24dp))
                    .apply(RequestOptions().fitCenter())
                    .apply(RequestOptions().override(220, 160))
                    .into(imageTeam)
        } else {
            Glide.with(applicationContext)
                    .load(team.strTeamBadge)
                    .apply(RequestOptions().placeholder(R.drawable.
                            ic_hourglass_empty_black_24dp))
                    .apply(RequestOptions().fitCenter())
                    .apply(RequestOptions().override(220, 160))
                    .into(imageTeam)
        }

        Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.
                        ic_hourglass_empty_black_24dp))
                .apply(RequestOptions().fitCenter())
                .apply(RequestOptions().override(220, 160))
                .into(iv_team)

        tv_name.text = team.strTeam
        tv_year.text = team.intFormedYear
        tv_stadium.text = team.strStadium

        val adapter = ViewPagerMatchAdapter(supportFragmentManager)
        adapter.populateFragment(PlayerFragment.newInstance(team), "Player Info")
        adapter.populateFragment(InfoTeamFragment.newInstance(team), "Team Info")
        viewpagerTeam.adapter = adapter
        tabsTeam.setupWithViewPager(viewpagerTeam)

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        favoriteState()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.favorite -> {
                isFavorite = if (!isFavorite) {
                    presenter.addToFavorite(this, team)
                    !isFavorite
                } else {
                    presenter.removeFavorite(this, team.idTeam)
                    !isFavorite
                }
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun favoriteState() = database.use {
        val result = select(TeamEntity.TABLE_TEAM).whereArgs("(TEAM_ID = {id})", "id" to team.idTeam)
        val favorite = result.parseList(classParser<TeamEntity>())
        if (!favorite.isEmpty()) isFavorite = true
        setFavorite()
    }

    private fun setFavorite() = if (isFavorite) {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
    } else {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
    }


}
