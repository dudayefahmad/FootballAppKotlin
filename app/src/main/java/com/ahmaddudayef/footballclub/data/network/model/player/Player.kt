package com.ahmaddudayef.footballclub.data.network.model.player

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
@Parcelize
data class Player(
        @SerializedName("strPlayer")
        var strPlayer: String?,
        @SerializedName("strDescriptionES")
        var strDescriptionES: String?,
        @SerializedName("dateBorn")
        var dateBorn: String?,
        @SerializedName("strNationality")
        var strNationality: String?,
        @SerializedName("strBanner")
        var strBanner: String?,
        @SerializedName("strSport")
        var strSport: String?,
        @SerializedName("strWeight")
        var strWeight: String?,
        @SerializedName("strDescriptionCN")
        var strDescriptionCN: String?,
        @SerializedName("strDescriptionIT")
        var strDescriptionIT: String?,
        @SerializedName("strInstagram")
        var strInstagram: String?,
        @SerializedName("idTeam")
        var idTeam: String?,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String?,
        @SerializedName("strBirthLocation")
        var strBirthLocation: String?,
        @SerializedName("strWebsite")
        var strWebsite: String?,
        @SerializedName("strHeight")
        var strHeight: String?,
        @SerializedName("strPosition")
        var strPosition: String?,
        @SerializedName("strYoutube")
        var strYoutube: String?,
        @SerializedName("strDescriptionIL")
        var strDescriptionIL: String?,
        @SerializedName("strCutout")
        var strCutout: String?,
        @SerializedName("idPlayerManager")
        var idPlayerManager: String?,
        @SerializedName("strLocked")
        var strLocked: String?,
        @SerializedName("intLoved")
        var intLoved: String?,
        @SerializedName("idSoccerXML")
        var idSoccerXML: String?,
        @SerializedName("strTeam")
        var strTeam: String?,
        @SerializedName("intSoccerXMLTeamID")
        var intSoccerXMLTeamID: String?,
        @SerializedName("strDescriptionHU")
        var strDescriptionHU: String?,
        @SerializedName("strTwitter")
        var strTwitter: String?,
        @SerializedName("strSigning")
        var strSigning: String?,
        @SerializedName("strGender")
        var strGender: String?,
        @SerializedName("strDescriptionSE")
        var strDescriptionSE: String?,
        @SerializedName("strDescriptionJP")
        var strDescriptionJP: String?,
        @SerializedName("strFanart1")
        var strFanart1: String?,
        @SerializedName("strDescriptionFR")
        var strDescriptionFR: String?,
        @SerializedName("strFanart2")
        var strFanart2: String?,
        @SerializedName("strFanart3")
        var strFanart3: String?,
        @SerializedName("strFacebook")
        var strFacebook: String?,
        @SerializedName("strFanart4")
        var strFanart4: String?,
        @SerializedName("strCollege")
        var strCollege: String?,
        @SerializedName("idPlayer")
        var idPlayer: String,
        @SerializedName("strDescriptionNL")
        var strDescriptionNL: String?,
        @SerializedName("strDescriptionRU")
        var strDescriptionRU: String?,
        @SerializedName("strDescriptionPT")
        var strDescriptionPT: String?,
        @SerializedName("strDescriptionDE")
        var strDescriptionDE: String?,
        @SerializedName("strDescriptionNO")
        var strDescriptionNO: String?,
        @SerializedName("strThumb")
        var strThumb: String?,
        @SerializedName("strWage")
        var strWage: String?,
        @SerializedName("dateSigned")
        var dateSigned: String?,
        @SerializedName("strDescriptionPL")
        var strDescriptionPL: String?
): Parcelable