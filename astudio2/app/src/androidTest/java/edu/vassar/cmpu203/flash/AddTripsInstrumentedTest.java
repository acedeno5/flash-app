package edu.vassar.cmpu203.flash;

import android.os.SystemClock;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.cmpu203.flash.controller.MainActivity;

/**
 * Instrumented test class for testing the Flash app's functionality
 */
@RunWith(AndroidJUnit4.class)
public class AddTripsInstrumentedTest {

    private ActivityScenario<MainActivity> activityScenario;

    @Before
    public void setup() {
        activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @After
    public void cleanup() {
        if (activityScenario != null) {
            activityScenario.close();
        }
    }

    /**
     * Tests basic trip functionality
     */
    @Test
    public void testBasicTripFunction() {
        // Get trip details view
        ViewInteraction tripDetailsVi = Espresso.onView(
                ViewMatchers.withId(R.id.tripDetailsText));

        // Check initial state
        tripDetailsVi.check(
                ViewAssertions.matches(
                        ViewMatchers.withText("")));

        // Start trip
        Espresso.onView(ViewMatchers.withId(R.id.startTripButton))
                .perform(ViewActions.click());

        // Small delay for location services
        SystemClock.sleep(1000);

        // Verify trip started
        tripDetailsVi.check(ViewAssertions.matches(
                ViewMatchers.withSubstring("Trip started")));

        // End trip
        Espresso.onView(ViewMatchers.withId(R.id.endTripButton))
                .perform(ViewActions.click());

        // Verify trip summary
        tripDetailsVi.check(ViewAssertions.matches(
                ViewMatchers.withSubstring("Distance:")));
    }

    /**
     * Tests basic navigation
     */
    @Test
    public void testNavigation() {
        // Navigate to leaderboard
        Espresso.onView(ViewMatchers.withId(R.id.viewLeaderboardButton))
                .perform(ViewActions.click());

        // Return to main screen
        Espresso.onView(ViewMatchers.withId(R.id.startNewTripButton))
                .perform(ViewActions.click());

        // Verify return to main screen
        Espresso.onView(ViewMatchers.withId(R.id.startTripButton))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
