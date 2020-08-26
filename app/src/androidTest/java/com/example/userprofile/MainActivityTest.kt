package com.example.userprofile

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**Sample test cases
 *
 */
const val NAME="kingsley izundu"
const val EMAIL="izundukingsleyemeka@gmail.com"
const val PHONE="2348060456301"
const val INVALID_PHONE="080606055765"
const val PACKAGE_NAME="com.example.userprofile"
const val MALE=1
private const val FEMALE=0


@RunWith(AndroidJUnit4::class)

class MainActivityTest{

    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
    val activityTestRule= ActivityTestRule(MainActivity::class.java)
    var context=activityTestRule.activity
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    lateinit var instrumentationContext: Context




    /**
     * set up activity scenerio to point to the main activity context; hence exposing the resources within that context
     */
    @Before
    fun setupViewModel() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    /**case when the activity is laucnhed, check that it is displayed
     *
     */
    @Test
    fun launchMainActivity_ActivityViewDisplay(){
        onView(withId(R.id.main)).check(matches(isDisplayed()))
        //can use matches(withEffectiveVisibility(Visibility.VISIBLE))
    }

    /**Case when the activity is launched, check that name edit text is displayed
     *
     */
    @Test
    fun launchMainActivity_NameEditTestDisplay(){
        //using withEffectiveVisibility to check
        onView(withId(R.id.etName)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    /**
     * when the activity is launched, check that phone edit text is displayed
     */
    @Test
    fun launchMainActivity_PhoneEditTestDisplay(){
        onView(withId(R.id.etPhone)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    /**
     * when the acitivty is launched, check that phone number accepts number
     */
    @Test
    fun launchMainActivity_PhoneAcceptsNumber(){
        onView(withId(R.id.etPhone)).perform(typeText("08060456301")).check(matches(withText("08060456301")));
    }


    /**
     * When activity is laucnched, check that email edit text is displayed
     */
    @Test
    fun launchMainActivity_EmailEditTestDisplay(){
        onView(withId(R.id.etEmail)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    /**
     * check that email address can be entered
     */

    @Test
    fun launchMainActivity_enterValidEmail(){
        onView(withId(R.id.etEmail)).perform(typeText("izundukingsleyemeka@gmail.com")).check(
            matches(withText("izundukingsleyemeka@gmail.com"))
        )
    }

    /**
     * check that the spinner is displayed
     */
    @Test
    fun launchMainActivity_SexSpinnerViewDisplay(){
        onView(withId(R.id.spSex)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    /**
     * check that button for display profile is displayed
     */

    @Test
    fun launchMainActivity_BtnDisplayProfileViewDisplay(){
        onView(withId(R.id.btnDisplayInfo)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    /**
     * check that hint for name edit text matches with string res
     */
    @Test
    fun launchMainActivity_NameHintMatchesStringResources(){
        onView(withId(R.id.etName)).check(matches(withHint(R.string.enter_your_name)))
    }

    /**
     * check that hint for phone edit text matches with string res
     */
    @Test
    fun launchMainActivity_PhoneHintMatchesStringResources(){
        onView(withId(R.id.etPhone)).check(matches(withHint(R.string.enter_your_phone_number)))
    }

    /**
     * check that hint for email edit text matches with string res
     */

    @Test
    fun launchMainActivity_EmailHintMatchesStringResources(){
        onView(withId(R.id.etEmail)).check(matches(withHint(R.string.enter_your_email)))
    }

    /**
     * check that text for btn display info matches with string res
     */

    @Test
    fun launchMainActivity_BtnDisplayInfoTextMatchesStringResources(){
        onView(withId(R.id.btnDisplayInfo)).check(matches(withText(R.string.display_info)))
    }

    /**
     * check that the spinner has two items for sex choice- female as first item, male as second item
     */

    @Test
    fun launchMainActivity_spSexMatchStringResource(){

        var adapter= ArrayAdapter.createFromResource(
            ApplicationProvider.getApplicationContext(),
            R.array.sex,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        assertEquals(adapter.getItem(0).toString(), ("Female"))
        assertEquals(adapter.getItem(1).toString(), ("Male"))

    }

    /**
     * check that spinner has two items
     */
    @Test
    fun launchMainActivity_spSexMatchStringResourceCount(){

        var adapter= ArrayAdapter.createFromResource(
            ApplicationProvider.getApplicationContext(),
            R.array.sex,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        assertEquals(adapter.count, (2))

    }

    /**
     * when female is selected, check that spinner text is Female
     */
    @Test
    fun spSexOnItemSelected_selectedSexIsFemale(){

        onView(withId(R.id.spSex)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spSex)).check(matches(withSpinnerText(containsString("Female"))));


    }

    /**
     * when female is selected, check that spinner text is Male
     */

    @Test
    fun spSexOnItemSelected_selectedSexIsMale(){

        onView(withId(R.id.spSex)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.spSex)).check(matches(withSpinnerText(containsString("Male"))));

    }


    /**
     * when button is clicked, and number entered is incorrect, check that toast message error is displayed
     */
    @Test
    fun btnDisplayInfoClicked_phoneNumberIncorrect_displayToastError(){
        //type the name in the name edit text
        onView(withId(R.id.etName)).perform(typeText(NAME))
        Espresso.closeSoftKeyboard()

        //type the phone number in the phone number edit text
        onView(withId(R.id.etPhone))
            .perform(typeText(INVALID_PHONE))
        Espresso.closeSoftKeyboard()


        //type the email in the email edit text
        onView(withId(R.id.etEmail))
            .perform(typeText(EMAIL))
        Espresso.closeSoftKeyboard()

        //choose male as sex
        onView(withId(R.id.spSex)).perform(click());
        onData(anything()).atPosition(MALE).perform(click());

        onView(withId(R.id.btnDisplayInfo)).perform(click())

        onView(withText(R.string.invalid_phone_number)).inRoot(ToastMatcher().apply {
            matches(isDisplayed())
        }
        );

    }


    /**
     * when button is clicked, and no name entered, check that appropriate toast message error is displayed
     */

    @Test
    fun btnDisplayInfoClicked_noNameEntered_displayToastError(){

        //type the phone number in the phone number edit text
        onView(withId(R.id.etPhone))
            .perform(typeText(PHONE))
        Espresso.closeSoftKeyboard()


        //type the email in the email edit text
        onView(withId(R.id.etEmail))
            .perform(typeText(EMAIL))
        Espresso.closeSoftKeyboard()

        //choose male as sex
        onView(withId(R.id.spSex)).perform(click());
        onData(anything()).atPosition(MALE).perform(click());

        onView(withId(R.id.btnDisplayInfo)).perform(click())

        onView(withText(R.string.no_name_entered)).inRoot(ToastMatcher().apply {
            matches(isDisplayed())
        }
        );

    }

    /**
     * when button is clicked, and no email entered, check that appropriate toast message error is displayed
     */
    @Test
    fun btnDisplayInfoClicked_noEmailEntered_displayToastError(){
        //type the name in the name edit text
        onView(withId(R.id.etName)).perform(typeText(NAME))
        Espresso.closeSoftKeyboard()

        //type the phone number in the phone number edit text
        onView(withId(R.id.etPhone))
            .perform(typeText(INVALID_PHONE))
        Espresso.closeSoftKeyboard()

        //choose male as sex
        onView(withId(R.id.spSex)).perform(click());
        onData(anything()).atPosition(MALE).perform(click());

        onView(withId(R.id.btnDisplayInfo)).perform(click())

        onView(withText(R.string.no_email_entered)).inRoot(ToastMatcher().apply {
            matches(isDisplayed())
        }
        );

    }

    /**
     * when button is clicked, and only sex selected, check that appropriate toast message error is displayed
     */
    @Test
    fun btnDisplayInfoClicked_onlySexSelected_displayToastError(){

        //choose male as sex
        onView(withId(R.id.spSex)).perform(click());
        onData(anything()).atPosition(FEMALE).perform(click());

        onView(withId(R.id.btnDisplayInfo)).perform(click())

        onView(withText(R.string.all_field_empty)).inRoot(ToastMatcher().apply {
            matches(isDisplayed())
        }
        );
    }

    /**
     * when button is clicked, and every detail entered, very that intended message for profile activity is sent
     */

    @Test
    fun btnDisplayInfoClicked_verifyMessageSentToProfileInfoActivity(){
        //type the name in the name edit text
        onView(withId(R.id.etName)).perform(typeText(NAME))
        Espresso.closeSoftKeyboard()

        //type the phone number in the phone number edit text
        onView(withId(R.id.etPhone))
            .perform(typeText(PHONE))
        Espresso.closeSoftKeyboard()


        //type the email in the email edit text
        onView(withId(R.id.etEmail))
            .perform(typeText(EMAIL))
        Espresso.closeSoftKeyboard()

        onView(withId(R.id.spSex)).perform(click());
        onData(anything()).atPosition(MALE).perform(click());


        //get the data using the data class
        val dataToSend=UserData(NAME, PHONE, EMAIL, "Male")

        //click the button to send the message to Profile Info Activity through explicit intent
        onView(withId(R.id.btnDisplayInfo)).perform(click())

        //verify that the ProfileInfo activity received an intent

        intended(
            allOf(
                hasComponent(hasShortClassName(".ProfileInfo")), toPackage(PACKAGE_NAME),
                hasExtra("USER_INFO", dataToSend)
            )
        )
    }
}