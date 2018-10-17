package com.ahmaddudayef.footballclub.ui.nextmatch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.ahmaddudayef.footballclub.R.id.*
import com.ahmaddudayef.footballclub.ui.home.HomeActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Ahmad Dudayef on 10/11/2018.
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        delay()
        onView(withId(spinnerNextMatch))
                .check(matches(isDisplayed()))
        delay()
        onView(withId(spinnerNextMatch)).perform(click())
        delay()
        onView(withText("Italian Serie A")).perform(click())
        delay()
        onView(withText("Juventus"))
                .check(matches(isDisplayed()))
        delay()
        onView(withText("Juventus")).perform(click())
        delay()
        onView(withId(favorite))
                .check(matches(isDisplayed()))
        delay()
        onView(withId(favorite)).perform(click())
        delay()
        pressBack()
    }

    private fun delay(){
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}