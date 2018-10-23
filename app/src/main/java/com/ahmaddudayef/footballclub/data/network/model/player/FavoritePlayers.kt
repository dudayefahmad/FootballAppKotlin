package com.ahmaddudayef.footballclub.data.network.model.player

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
data class FavoritePlayers(
        @SerializedName("players") var players: MutableList<Player>? = null)