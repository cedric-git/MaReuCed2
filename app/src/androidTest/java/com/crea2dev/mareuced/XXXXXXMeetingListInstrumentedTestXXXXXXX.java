package com.crea2dev.mareuced;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static com.crea2dev.mareuced.RecyclerViewUtils;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static com.crea2dev.mareuced.MainActivity_MeetingListTest.childAtPosition;
import static com.crea2dev.mareuced.RecyclerViewUtils.clickChildView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

//import androidx.test.runner.AndroidJUnit4;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)


public class XXXXXXMeetingListInstrumentedTestXXXXXXX {

    @Rule
    public ActivityTestRule<MainActivity_MeetingList> mActivityRule = new ActivityTestRule<>(MainActivity_MeetingList.class);

    private int currentMeetingsSize = 3;
    private MeetingApiService mApiService;

    @Before
    public void setup(){
        mApiService = Injection.getMeetingApiService();
        mActivityRule.getActivity();
        currentMeetingsSize = mApiService.getMeetings().size();
    }

//    @Test
//    public void Save_Meeting_Button(){
//
//        //Adding a meeting in AddMeetingFragment
//        onView(withId(R.id.FAB_add_Meeting)).perform(click());
//        onView(withId(R.id.newMeeting_Name)).perform(typeText("Meeting1"));
//        onView(withId(R.id.newMeeting_Place)).perform(typeText("Salle1"));
////        Place
////        click on time
////        Time picker value
////        onView(withId(R.id.time_zone)).perform(...???.setPickerValue(5));
//        onView(withId(R.id.display_participant_list_text_view)).perform(typeText("zozo@gmail.com, Sydney@hotmail.com"));
//        onView(withId(R.id.button_add_meeting)).perform(click());
//
//        //Check Meetings list counts one more
//        onView(withId(R.id.fragment_main_recycler_view)).check((ViewAssertion) new ItemCount(currentMeetingsSize + 1));
//    }

//
    @Test
        public void check_Delete_Meeting_Button(){
            onView(withId(R.id.fragment_main_recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildView(R.id.item_list_delete_button)));
            onView(withId(R.id.fragment_main_recycler_view)).check((ViewAssertion) new RecyclerViewUtils.ItemCount(currentMeetingsSize - 1));
        }
//
//    @Test
//    public void check_Add_Meeting_Button() {
//    }


}