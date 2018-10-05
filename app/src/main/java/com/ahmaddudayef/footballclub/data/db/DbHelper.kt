package com.ahmaddudayef.footballclub.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.ahmaddudayef.footballclub.data.db.entities.MatchEntity
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
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchEntity.TABLE_MATCH, true)
    }
}

val Context.database: DbHelper get() = DbHelper.getInstance(applicationContext)