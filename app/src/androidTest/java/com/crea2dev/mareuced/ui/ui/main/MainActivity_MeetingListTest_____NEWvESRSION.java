package com.crea2dev.mareuced.ui.ui.main;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.crea2dev.mareuced.ItemCount;
import com.crea2dev.mareuced.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivity_MeetingListTest_____NEWvESRSION {

    @Rule
    public ActivityTestRule<MainActivity_MeetingList> mActivityTestRule = new ActivityTestRule<>(MainActivity_MeetingList.class);
    private int currentMeetingsSize = 5;
    @Test
    // click on add Meeting FAB, fill the fields, Add Participant, Save meeting <<<<<<<<<<<<<
    public void mainActivity_MeetingListTest_____NEWvESRSION() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.FAB_add_Meeting),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withId(R.id.container),
                                                1)),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.newMeeting_Name),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Desig"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.newMeeting_Place),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Salle 1"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.time_zone),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.newMeeting_Time),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatRadioButton")), withText("PM"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RadioGroup")),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.input_Participant),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.newMeeting_Participants),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("jo@gma.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button_add_participant), withText("  ADD PARTICIPANT"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button_add_meeting), withText("   SAVE MEETING      "),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton3.perform(click());

        //Check Meetings list(via ItemCount) counts 1 more   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        onView(withId(R.id.fragment_main_recycler_view)).check((ViewAssertion) new ItemCount(currentMeetingsSize + 1));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
