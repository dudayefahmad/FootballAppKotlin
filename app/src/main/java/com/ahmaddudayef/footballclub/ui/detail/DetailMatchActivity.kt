package com.ahmaddudayef.footballclub.ui.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.MatchEntity
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.BaseActivity
import com.ahmaddudayef.footballclub.utils.CommonUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import javax.inject.Inject

class DetailMatchActivity : BaseActivity(), DetailMatchMvpView{

    @Inject
    lateinit var presenter: DetailMatchMvpPresenter<DetailMatchMvpView>
    lateinit var eventsItem: EventsItem
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        eventsItem = intent.getParcelableExtra("match_detail")
        presenter.onAttach(this)
        init(eventsItem)
    }

    private fun init(item: EventsItem) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.getTeamsBadge(item.idHomeTeam, item.idAwayTeam)

        txtDate.text = item.dateEvent

        //home
        txtHomeTeam.text = item.strHomeTeam
        txtHomeScorer.text = CommonUtils.comma(item.strHomeGoalDetails)
        txtHomeScore.text = item.intHomeScore

        txtGoalHomeKeeper.text = item.strHomeLineupGoalkeeper
        txtHomeDefense.text = CommonUtils.comma(item.strHomeLineupDefense)
        txtHomeMidfield.text = CommonUtils.comma(item.strHomeLineupMidfield)
        txtHomeForward.text = CommonUtils.comma(item.strHomeLineupForward)
        txtHomeSubtitles.text = CommonUtils.comma(item.strHomeLineupSubstitutes)

        //away
        txtAwayTeam.text = item.strAwayTeam
        txtAwayScorer.text = CommonUtils.comma(item.strAwayGoalDetails)
        txtAwayScore.text = item.intAwayScore

        txtGoalAwayKeeper.text = item.strAwayLineupGoalkeeper
        txtAwayDefense.text = CommonUtils.comma(item.strAwayLineupDefense)
        txtAwayMidfield.text = CommonUtils.comma(item.strAwayLineupMidfield)
        txtAwayForward.text = CommonUtils.comma(item.strAwayLineupForward)
        txtAwaySubtitles.text = CommonUtils.comma(item.strAwayLineupSubstitutes)
    }

    override fun showDetailMatch(homeBadge: String, awayBadge: String) {
        Glide.with(applicationContext)
                .load(homeBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                .into(imgHomeTeam)

        Glide.with(applicationContext)
                .load(awayBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                .into(imgAwayTeam)
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
                    presenter.addToFavorite(this, eventsItem)
                    !isFavorite
                } else {
                    presenter.removeFromFavorite(this, eventsItem.idEvent)
                    !isFavorite
                }
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun favoriteState() = database.use {
        val result = select(MatchEntity.TABLE_MATCH).whereArgs("(EVENT_ID = {id})", "id" to eventsItem.idEvent)
        val favorite = result.parseList(classParser<MatchEntity>())
        if (!favorite.isEmpty()) isFavorite = true
        setFavorite()
    }

    private fun setFavorite() = if (isFavorite) {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
    } else {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
    }
}
