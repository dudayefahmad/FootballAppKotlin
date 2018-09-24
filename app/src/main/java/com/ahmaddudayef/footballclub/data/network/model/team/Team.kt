package com.ahmaddudayef.footballclub.test.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmad Dudayef on 9/8/2018.
 */
data class Team(
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)