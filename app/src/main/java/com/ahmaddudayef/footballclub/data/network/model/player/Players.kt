package com.ahmaddudayef.footballclub.data.network.model.player

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
data class Players(
        @SerializedName("player") var players: MutableList<Player>)