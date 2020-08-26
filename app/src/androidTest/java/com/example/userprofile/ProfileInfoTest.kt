package com.example.userprofile

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.Serializable

@RunWith(AndroidJUnit4ClassRunner::class)
class ProfileInfoTest{

    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
    val activityTestRule= ActivityTestRule(ProfileInfo::class.java)
    var context=activityTestRule.activity

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    lateinit var instrumentationContext: Context

    @Before
    fun setupViewModel() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }


    /**
     * When profile is launched, check that it displays appropraite messages received from intent
     */

    @Test
    fun launchProfileActivity_showingProperText(){
        //type the name in the name edit text
        onView(withId(R.id.etName)).perform(ViewActions.typeText(NAME))
        Espresso.closeSoftKeyboard()

        //type the phone number in the phone number edit text
        onView(withId(R.id.etPhone))
            .perform(ViewActions.typeText(PHONE))
        Espresso.closeSoftKeyboard()


        //type the email in the email edit text
        onView(withId(R.id.etEmail))
            .perform(ViewActions.typeText(EMAIL))
        Espresso.closeSoftKeyboard()

        //choose male as sex
        onView(withId(R.id.spSex)).perform(ViewActions.click());
        Espresso.onData(CoreMatchers.anything()).atPosition(MALE).perform(ViewActions.click());

        //click the display info button
        onView(withId(R.id.btnDisplayInfo)).perform(ViewActions.click())

        //check that profile activity is displayed
        onView(withId(R.id.profileActivity)).check(matches(isDisplayed()))

        //check that intent data is displayed on appropriate text views
        onView(withId(R.id.tvProfileHeader)).check(matches(withText(R.string.registration_details)))
        onView(withId(R.id.tvSex)).check(matches(withText("Male")))
        onView(withId(R.id.tvDisplayName)).check(matches(withText(NAME)))
        onView(withId(R.id.tvDisplayEmail)).check(matches(withText(EMAIL)))
        onView(withId(R.id.tvDisplayPhone)).check(matches(withText(PHONE)))

    }

    /**
     * when back navigation is clicked, check that it returns to main activity
     */
    @Test
    fun pressBackNavigation_returnMainActivity(){
        pressBack()
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }




}