package com.kasanderh.gettherecompass.presenter

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kasanderh.gettherecompass.R
import com.kasanderh.gettherecompass.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityPresenterTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun showDialog() {
        val EXPECTED_COORDINATES = "40.4167, -3.7037"


        Espresso.onView(ViewMatchers.withId(R.id.button_add_destination))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText(R.string.please_enter_coordinates))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.input_latitude))
            .perform(ViewActions.typeText("40.4167"))
        Espresso.onView(ViewMatchers.withId(R.id.input_longitude))
            .perform(ViewActions.typeText("-3.7037"))

        Espresso.onView(ViewMatchers.withId(R.id.button_ok)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText(R.string.please_enter_coordinates))
            .check(ViewAssertions.doesNotExist())

        Espresso.onView(ViewMatchers.withId(R.id.text_view_destination_coordinates)).check(
            ViewAssertions.matches(ViewMatchers.withText(EXPECTED_COORDINATES))
        )
    }



//    @Test
//    fun closeDialog() {
//    }
//
//    @Test
//    fun requestLocationPermission() {
//    }
//
//    @Test
//    fun onPermissionResult() {
//    }
//
//    @Test
//    fun onInputDialogConfirmed() {
//
//    }
//
//    @Test
//    fun onInputDialogCancelled() {
//    }
//
//
//    @Test
//    fun onInputDialogError() {
//    }
//
//    @Test
//    fun rotationCompass() {
//    }
//
//    @Test
//    fun rotationArrow() {
//    }
//
//    @Test
//    fun locationChanged_notNull() {
//
//    }
}