package com.ahmaddudayef.footballclub.ui.detailplayer

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.PlayerEntity
import com.ahmaddudayef.footballclub.data.db.entities.TeamEntity
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
class DetailPlayerPresenter<V: DetailPlayerMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), DetailPlayerMvpPresenter<V> {

    override fun addToFavorite(context: Context, player: Player) {
        try {
            context.database.use {
                insert(PlayerEntity.TABLE_PLAYER,
                        PlayerEntity.PLAYER_ID to player.idPlayer,
                        PlayerEntity.PLAYER_IMAGE_URL to player.strCutout,
                        PlayerEntity.PLAYER_POSITION to player.strPosition)
            }
            mvpView?.showMessage("Added to Favorite Player")
        } catch (e: SQLiteConstraintException) {
            mvpView?.showError(e.localizedMessage)
        }
    }

    override fun removeFavorite(context: Context, id: String) {
        try {
            context.database.use {
                delete(PlayerEntity.TABLE_PLAYER, "(PLAYER_ID = {id})", "id" to id)
            }
            mvpView?.showMessage("Removed from favorite player")
        } catch (e: SQLiteConstraintException) {
            mvpView?.showError(e.localizedMessage)
        }
    }

}