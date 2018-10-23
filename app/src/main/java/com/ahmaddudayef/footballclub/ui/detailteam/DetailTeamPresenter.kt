package com.ahmaddudayef.footballclub.ui.detailteam

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.TeamEntity
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 10/18/2018.
 */
class DetailTeamPresenter<V: DetailTeamMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), DetailTeamMvpPresenter<V> {

    override fun addToFavorite(context: Context, team: Team) {
        try {
            context.database.use {
                insert(TeamEntity.TABLE_TEAM,
                        TeamEntity.TEAM_ID to team.idTeam,
                        TeamEntity.TEAM_IMAGE_URL to team.strTeamBadge)
            }
            mvpView?.showMessage("Added to Favorite Team")
        } catch (e: SQLiteConstraintException) {
            mvpView?.showError(e.localizedMessage)
        }
    }

    override fun removeFavorite(context: Context, id: String) {
        try {
            context.database.use {
                delete(TeamEntity.TABLE_TEAM, "(TEAM_ID = {id})", "id" to id)
            }
            mvpView?.showMessage("Removed from favorite team")
        } catch (e: SQLiteConstraintException) {
            mvpView?.showError(e.localizedMessage)
        }
    }
}