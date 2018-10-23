package com.ahmaddudayef.footballclub.ui.detailplayer

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.PlayerEntity
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail_player.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import javax.inject.Inject

class DetailPlayerActivity : BaseActivity(), DetailPlayerMvpView {

    @Inject
    lateinit var presenter: DetailPlayerMvpPresenter<DetailPlayerMvpView>
    lateinit var player: Player
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        player = intent.getParcelableExtra("player_detail")
        presenter.onAttach(this)
        init(player)
    }

    private fun init(player: Player) {
        setSupportActionBar(toolbar)
        setSupportActionBar(toolbar2)
        toolbar2.title = player.strPlayer
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
                    collapsingToolbarLayout.setTitle(player.strPlayer)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ")//carefull there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        })

        if (!player.strFanart1.equals(null)){
            Glide.with(applicationContext)
                    .load(player.strFanart1)
                    .apply(RequestOptions().placeholder(R.drawable.
                            ic_hourglass_empty_black_24dp))
                    .apply(RequestOptions().fitCenter())
                    .apply(RequestOptions().override(220, 160))
                    .into(imageBackPlayer)
        } else {
            Glide.with(applicationContext)
                    .load(player.strCutout)
                    .apply(RequestOptions().placeholder(R.drawable.
                            ic_hourglass_empty_black_24dp))
                    .apply(RequestOptions().fitCenter())
                    .apply(RequestOptions().override(220, 160))
                    .into(imageBackPlayer)
        }

        Glide.with(applicationContext)
                .load(player.strCutout)
                .apply(RequestOptions().placeholder(R.drawable.
                        ic_hourglass_empty_black_24dp))
                .apply(RequestOptions().fitCenter())
                .apply(RequestOptions().override(220, 160))
                .into(iv_player)

        player_name.text = player.strPlayer
        player_nationality.text = player.strNationality
        weight_player.text = player.strWeight
        height_player.text = player.strHeight
        player_overview.text = player.strDescriptionEN
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
                    presenter.addToFavorite(this, player)
                    !isFavorite
                } else {
                    presenter.removeFavorite(this, player.idPlayer)
                    !isFavorite
                }
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun favoriteState() = database.use {
        val result = select(PlayerEntity.TABLE_PLAYER).whereArgs("(PLAYER_ID = {id})", "id" to player.idPlayer)
        val favorite = result.parseList(classParser<PlayerEntity>())
        if (!favorite.isEmpty()) isFavorite = true
        setFavorite()
    }

    private fun setFavorite() = if (isFavorite) {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
    } else {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
    }
}
