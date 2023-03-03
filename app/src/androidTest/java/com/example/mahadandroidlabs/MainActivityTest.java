package com.example.mahadandroidlabs;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

        ViewInteraction appCompatEditText2 = onView(withId(R.id.editText));
        appCompatEditText2.perform(replaceText("12345"), closeSoftKeyboard());


        ViewInteraction materialButton = onView(withId(R.id.button));
            materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You Shall Not Pass!")));
    }

    /**
     * The is a test to find to find the edit text, place text within that does not contain Uppercase,
     * and after the button is checked, check if the text is the same as the one below.
     * Test should pass if the password has no uppercase letters and the text is correct.
     */
    @Test
    public void testFindMissingUpperCase() {
        //Find View
        ViewInteraction appCompatEditText2 = onView(withId(R.id.editText));
        //Type Password
        appCompatEditText2.perform(replaceText("password123#$*"));

        //Find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click Button
        materialButton.perform(click());

        //Find Textview
        ViewInteraction textView = onView(withId(R.id.textView));
        //Check text
        textView.check(matches(withText("You Shall Not Pass!")));

    }

    /**
     * The is a test to find to find the edit text, place text within that does not contain lowerrcase,
     * and after the button is checked, check if the text is the same as the one below.
     * Test should pass if the password has no lowercase letters and the text is correct.
     */
    @Test
    public void testFindMissingLowerCase() {
        //Find View
        ViewInteraction appCompatEditText2 = onView(withId(R.id.editText));
        //Type Password
        appCompatEditText2.perform(replaceText("PASSWORD123#$*"));

        //Find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click Button
        materialButton.perform(click());

        //Find Textview
        ViewInteraction textView = onView(withId(R.id.textView));
        //Check text
        textView.check(matches(withText("You Shall Not Pass!")));

    }

    /**
     * The is a test to find to find the edit text, place text within that does not contain numbers,
     * and after the button is checked, check if the text is the same as the one below.
     * Test should pass if the password has no numbers and the text is correct
     */
    @Test
    public void testFindMissingNumbers() {
        //Find View
        ViewInteraction appCompatEditText2 = onView(withId(R.id.editText));
        //Type Password
        appCompatEditText2.perform(replaceText("passwordASD#$*"));

        //Find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click Button
        materialButton.perform(click());

        //Find Textview
        ViewInteraction textView = onView(withId(R.id.textView));
        //Check text
        textView.check(matches(withText("You Shall Not Pass!")));

    }

    /**
     * The is a test to find to find the edit text, place text within that does not contain Special Character,
     * and after the button is checked, check if the text is the same as the one below.
     * Test should pass if the password has no Special Characters and the text is correct
     */
    @Test
    public void testFindMissingSpecial() {
        //Find View
        ViewInteraction appCompatEditText2 = onView(withId(R.id.editText));
        //Type Password
        appCompatEditText2.perform(replaceText("password123ASDF"));

        //Find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click Button
        materialButton.perform(click());

        //Find Textview
        ViewInteraction textView = onView(withId(R.id.textView));
        //Check text
        textView.check(matches(withText("You Shall Not Pass!")));

    }

    /**
     * The is a test to find to find the edit text, place text within that
     * Contains Uppercase, Lowercase, SPecial Characters and Numbers
     * and after the button is checked, check if the text is the same as the one below.
     * Test should pass if the password has all requirements and the text is correct
     */
    @Test
    public void testSucess() {
        //Find View
        ViewInteraction appCompatEditText2 = onView(withId(R.id.editText));
        //Type Password
        appCompatEditText2.perform(replaceText("passWORD123!@#$"));

        //Find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click Button
        materialButton.perform(click());

        //Find Textview
        ViewInteraction textView = onView(withId(R.id.textView));
        //Check text
        textView.check(matches(withText("Your password meets the requirements")));

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
