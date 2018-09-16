package com.ahmaddudayef.footballclub.network

import android.net.Uri
import com.ahmaddudayef.footballclub.BuildConfig

/**
 * Created by Ahmad Dudayef on 9/8/2018.
 */
object TheSportDBApi {

//    fun getTeams(league: String?): String {
//        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?1=" + league
//    }

    /**
     * Penulisan fungsi di atas bisa juga dilakukan dengan cara yang lebih sederhana,
     * yaitu dengan memanfaatkan fungsi Uri.Builder dari Android. Sehingga, kodenya menjadi seperti berikut:
     * */

    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }


}