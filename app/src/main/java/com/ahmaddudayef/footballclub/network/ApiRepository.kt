package com.ahmaddudayef.footballclub.network

import java.net.URL

/**
 * Created by Ahmad Dudayef on 9/8/2018.
 */
class ApiRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}