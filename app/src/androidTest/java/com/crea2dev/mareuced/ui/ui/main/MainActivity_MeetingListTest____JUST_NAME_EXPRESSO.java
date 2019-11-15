package com.crea2dev.mareuced.ui.ui.main;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivity_MeetingListTest____JUST_NAME_EXPRESSO {

    @Rule
    public ActivityTestRule<MainActivity_MeetingList> mActivityTestRule = new ActivityTestRule<>(MainActivity_MeetingList.class);

    @Test
    public void mainActivity_MeetingListTest____JUST_NAME_EXPRESSO() {
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
        textInputEditText.perform(replaceText("Design"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.newMeeting_Place),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Salle 3"), closeSoftKeyboard());
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
