package com.ahmaddudayef.footballclub.data.db.entities

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
data class TeamEntity(val id: Long?, val teamId: String?, val teamImageUrl: String?){
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_IMAGE_URL: String = "IMAGE_ID"
    }
}