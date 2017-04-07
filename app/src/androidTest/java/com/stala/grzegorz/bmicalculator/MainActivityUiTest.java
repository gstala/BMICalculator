package com.stala.grzegorz.bmicalculator;

/**
 * Created by Grzegorz on 30.03.2017.
 */

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityUiTest {

    private float validMassInKg;
    private float validHeightInM;
    private float validMassInLb;
    private float validHeightInIn;
    private float invalidMassInKg;
    private float invalidHeightInM;
    private float invalidMassInLb;
    private float invalidHeightInIn;

    @Before
    public void initValues() {
        validMassInKg = 50;
        validHeightInM = 1.75f;
        validMassInLb = 22;
        validHeightInIn = 20;
        invalidMassInKg = 300;
        invalidHeightInM = 0.2f;
        invalidMassInLb = 20;
        invalidHeightInIn = 100;
    }

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateMassEditTextWhenKgM() {
        onView(withId(R.id.KgMRadioButton)).perform(click());
        onView(withId(R.id.massEditText)).perform(typeText(String.valueOf(validMassInKg))).check(matches(withText(String.valueOf(validMassInKg))));
    }

    @Test
    public void validateHeightEditTextWhenKgM() {
        onView(withId(R.id.KgMRadioButton)).perform(click());
        onView(withId(R.id.heightEditText)).perform(typeText(String.valueOf(validHeightInM))).check(matches(withText(String.valueOf(validHeightInM))));
    }

    @Test
    public void resultIsDisplayedAfterEnteringValidDataInKgM() {
        onView(withId(R.id.KgMRadioButton)).perform(click());
        onView(withId(R.id.massEditText)).perform(typeText(String.valueOf(validMassInKg)));
        onView(withId(R.id.heightEditText)).perform(typeText(String.valueOf(validHeightInM)));
        onView(withId(R.id.buttonCalculate)).perform(click());
        onView(withId(R.id.resultTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void resultIsDisplayedAfterEnteringValidDataInLbIn() {
        onView(withId(R.id.LbInRadioButton)).perform(click());
        onView(withId(R.id.massEditText)).perform(typeText(String.valueOf(validMassInLb)));
        onView(withId(R.id.heightEditText)).perform(typeText(String.valueOf(validHeightInIn)));
        onView(withId(R.id.buttonCalculate)).perform(click());
        onView(withId(R.id.resultTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void toastIsDisplayedAfterEnteringNoData() {
        onView(withId(R.id.buttonCalculate)).perform(click());
        onView(withText(R.string.toastErrorText))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void toastIsDisplayedAfterEnteringInvalidDataInKgM() {
        onView(withId(R.id.KgMRadioButton)).perform(click());
        onView(withId(R.id.massEditText)).perform(typeText(String.valueOf(invalidMassInKg)));
        onView(withId(R.id.heightEditText)).perform(typeText(String.valueOf(invalidHeightInM)));
        onView(withId(R.id.buttonCalculate)).perform(click());
        onView(withText(R.string.toastErrorText))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void LbInRadioButtonIsCheckedAfterClick() {
        onView(withId(R.id.LbInRadioButton)).perform(click()).check(matches(isChecked()));
    }

    @Test
    public void KgMRadioButtonIsCheckedAfterClick() {
        onView(withId(R.id.KgMRadioButton)).perform(click()).check(matches(isChecked()));
    }

}
