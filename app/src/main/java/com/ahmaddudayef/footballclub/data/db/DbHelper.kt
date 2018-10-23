package com.ahmaddudayef.footballclub.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.ahmaddudayef.footballclub.data.db.entities.MatchEntity
import com.ahmaddudayef.footballclub.data.db.entities.PlayerEntity
import com.ahmaddudayef.footballclub.data.db.entities.TeamEntity
import org.jetbrains.anko.db.*

/**
 * Created by Ahmad Dudayef on 10/5/2018.
 */
class DbHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "favorite.db", null, 1){

    companion object {
        private var instance: DbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instance == null) instance = DbHelper(ctx.applicationContext)
            return instance as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MatchEntity.TABLE_MATCH, true,
                MatchEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchEntity.EVENT_ID to TEXT + UNIQUE,
                MatchEntity.MATCH_HOME_ID to TEXT,
                MatchEntity.MATCH_AWAY_ID to TEXT)

        db.createTable(TeamEntity.TABLE_TEAM, true,
                TeamEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamEntity.TEAM_ID to TEXT,
                TeamEntity.TEAM_IMAGE_URL to TEXT)

        db.createTable(PlayerEntity.TABLE_PLAYER, true,
                PlayerEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                PlayerEntity.PLAYER_ID to TEXT,
                PlayerEntity.PLAYER_IMAGE_URL to TEXT,
                PlayerEntity.PLAYER_POSITION to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchEntity.TABLE_MATCH, true)
        db.dropTable(TeamEntity.TABLE_TEAM, true)
        db.dropTable(PlayerEntity.TABLE_PLAYER, true)
    }
}

val Context.database: DbHelper get() = DbHelper.getInstance(applicationContext)