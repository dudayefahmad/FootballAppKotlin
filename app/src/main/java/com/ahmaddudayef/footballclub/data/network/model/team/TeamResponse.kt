package com.ahmaddudayef.footballclub.data.network.model.team

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmad Dudayef on 9/8/2018.
 */

data class TeamResponse(
        @SerializedName("teams") var teams: MutableList<Team>? = null)