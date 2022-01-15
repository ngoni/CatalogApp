package com.scribblex.catalogapp.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.scribblex.catalogapp.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class CatalogListFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testToolbarTitleIsShowing() {
        val newTitle = "Victory title"
        Espresso.onView(
            CoreMatchers.allOf(
                withId(R.id.toolbar),
                ViewMatchers.withText(newTitle)
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}