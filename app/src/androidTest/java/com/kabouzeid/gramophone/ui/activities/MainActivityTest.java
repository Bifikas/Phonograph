package com.kabouzeid.gramophone.ui.activities;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import com.kabouzeid.gramophone.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void remPlay() {
        onView(withText("Get started")).perform(click());
        onView(withText("PLAYLISTS")).perform(click());
        onView(withText("More options")).perform(click());
        onView(withText("New Playlist")).perform(click());
        onView(withText("Playlist name")).perform(replaceText("test"));
        onView(withText("CREATE")).perform(click());
        onView(withText("New Playlist")).check(matches(withText("test")));
        onView(withText("test")).perform(click());
        onView(withText("More options")).perform(click());
        onView(withText("Rename")).perform(click());
        onView(withText("Playlist name")).perform(replaceText("test1")));
        onView(withText("New Playlist")).check(matches(withText("test1")));
        onView(withText("Rename")).perform(click());
        onView(withText("More options")).perform(click());
        onView(withText("Delete")).perform(click());
        onView(withText("Delete")).perform(click());
        onView(withText("test")).check(doesNotExist(withText("test1")));
    }

    @Test
    public void addPlay() {
        onView(withText("Get started")).perform(click());
        onView(withText("PLAYLISTS")).perform(click());
        onView(withText("More options")).perform(click());
        onView(withText("New Playlist")).perform(click());
        onView(withText("Playlist name")).perform(replaceText("test"));
        onView(withText("CREATE")).perform(click());
        onView(withText("test")).perform(click());
        onView(withText("SONGS")).perform(click());
        onView(withText("Add to playlist...")).perform(click());
        onView(withText("test")).perform(click());
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
