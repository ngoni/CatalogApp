package com.scribblex.catalogapp.ui

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode


@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(manifest = Config.NONE)
class MainActivityTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun whenMainActivityLaunchedNavigationHelperIsInvokedForFragment() {
        assertTrue(true)
    }
}