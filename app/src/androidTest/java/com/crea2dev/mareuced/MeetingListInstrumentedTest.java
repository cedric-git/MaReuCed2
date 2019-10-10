package com.crea2dev.mareuced;

import android.content.Context;

import androidx.test.espresso.ViewAssertion;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
@RunWith(AndroidJUnit4.class)
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

public class MeetingListInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity_MeetingList> mActivityRule = new ActivityTestRule<>(MainActivity_MeetingList.class);

    private int currentMeetingsSize = 3;
    private MeetingApiService mApiService;

    @Before
    public void setup(){
        mApiService = Injection.getMeetingApiService();
//        mActivityRule.getActivity();
        currentMeetingsSize = mApiService.getMeetings().size();
    }

    @Test
    public void AddMeeting_With_Success(){

        //Adding a meeting in AddMeetingFragment
        onView(withId(R.id.nameInput)).perform(typeText("Meeting 1"));
//        Place
//        click on time
//        Time picker value
//        onView(withId(R.id.time_zone)).perform(...???.setPickerValue(5));
        onView(withId(R.id.display_participant_list_text_view)).perform(typeText("zozo@gmail.com, Sydney@hotmail.com"));
        onView(withId(R.id.button_add_meeting)).perform(click());

        //Check Meetings list counts one more
        onView(withId(R.id.fragment_main_recycler_view)).check((ViewAssertion) new ItemCount(currentMeetingsSize + 1));
    }

//    @Test
//        public void check_Delete_Meeting_Button(){
//            onView(withId(R.id.fragment_main_recycler_view))
//                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildView(R.id.item_list_delete_button)));
//            onView(withId(R.id.fragment_main_recycler_view)).check((ViewAssertion) new RecyclerViewUtils.ItemCount(currentMeetingsSize - 1));
//        }

    @Test
    public void check_Add_Meeting_Button() {
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.crea2dev.mareuced", appContext.getPackageName());
    }
}