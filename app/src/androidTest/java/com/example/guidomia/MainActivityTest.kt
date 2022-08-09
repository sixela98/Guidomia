package com.example.guidomia

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setUp() {
        
    }

    @Test
    fun recycleViewTest() {
        val scenario = activityRule.scenario
        scenario.onActivity { activity ->
            var recyclerView: RecyclerView = activity.findViewById(R.id.recyclerView)
            var itemCount = recyclerView.adapter?.itemCount
            if(itemCount != null) {

                Espresso.onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount.minus(1)))
            }
        }
    }

    @After
    fun tearDown() {
    }
}