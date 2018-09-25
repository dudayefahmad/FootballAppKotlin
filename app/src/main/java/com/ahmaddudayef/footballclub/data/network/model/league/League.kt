package com.ahmaddudayef.footballclub.data.network.model.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by Ahmad Dudayef on 9/24/2018.
 */
@Parcelize
data class League(
        @SerializedName("idLeague") var idLeague: String,
        @SerializedName("strLeague") var strLeague: String,
        @SerializedName("strSport") var strSport: String,
        @SerializedName("strLeagueAlternate") var strLeagueAlternate: String
): Parcelable
