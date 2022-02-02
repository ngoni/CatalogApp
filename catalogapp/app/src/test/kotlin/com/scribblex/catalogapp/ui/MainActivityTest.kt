package com.scribblex.catalogapp.ui

import androidx.core.view.isVisible
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.appbar.MaterialToolbar
import com.google.common.truth.Truth.assertThat
import com.scribblex.catalogapp.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.Robolectric


@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(manifest = Config.NONE)
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun checkToolBarisVisibleWhenMainActivityLaunched() {
        val activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java)
        assertThat(activity.requireViewById<MaterialToolbar>(R.id.toolbar).isVisible).isTrue()
    }

    @Test
    fun whenMainActivityLaunchedNavigationControllerOpensNavHostFragment() {
        activityScenarioRule.scenario
    }
}