package irul.com.footballmatchschedule

import android.support.design.widget.TabLayout
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import irul.com.footballmatchschedule.R.id.add_to_favorite
import org.junit.Test
import android.support.test.espresso.assertion.ViewAssertions.matches
import org.junit.Rule
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.*
import android.view.View
import org.hamcrest.Matcher
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.PerformException
import irul.com.footballmatchschedule.activity.MainActivity
import org.junit.After
import org.junit.Before
import android.support.test.espresso.Espresso.onData
import org.hamcrest.Matchers.*


class BehaviourTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {
        //withId = pengecekan by ID
        //withText pengecekan by Text
        onView(withId(R.id.navigation_mathces)).perform(click())
        onView(withId(R.id.listEventPast)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.listEventPast)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1))
        onView(withId(R.id.listEventNext)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(11))
        onView(withId(R.id.listEventNext)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(11, click()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.navigation_teams)).perform(click())
        onView(withId(R.id.spinnerLeague)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Spanish La Liga"))).perform(click())
        onView(withId(R.id.listTeamLeague)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.listTeamLeague)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.listTeamLeague)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(R.id.listTeamLeague)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.listEventFavorite)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.listEventFavorite)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.swipe_refresh_favorite_matches)).perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1))
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }


    fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }

    fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                        ?: throw PerformException.Builder()
                                .withCause(Throwable("No tab at index $tabIndex"))
                                .build()

                tabAtIndex.select()
            }
        }
    }
}