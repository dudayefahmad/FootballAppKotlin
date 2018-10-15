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
        onView(withId(spinnerNextMatch))
                .check(matches(isDisplayed()))
        onView(withId(spinnerNextMatch)).perform(click())
        onView(withText("Italian Serie A")).perform(click())

        onView(withText("Juventus"))
                .check(matches(isDisplayed()))
        onView(withText("Juventus")).perform(click())

        onView(withId(favorite))
                .check(matches(isDisplayed()))
        onView(withId(favorite)).perform(click())
        pressBack()
    }

}