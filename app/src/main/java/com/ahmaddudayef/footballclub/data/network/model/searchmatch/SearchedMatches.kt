package com.ahmaddudayef.footballclub.data.network.model.searchmatch

import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmad Dudayef on 10/21/2018.
 */
data class SearchedMatches(
        @SerializedName("event") var events: MutableList<EventsItem>
)