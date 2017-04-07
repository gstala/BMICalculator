package com.stala.grzegorz.bmicalculator;

import com.stala.grzegorz.bmicalculator.CountBmiForKgM;
import com.stala.grzegorz.bmicalculator.ICountBmi;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Grzegorz on 16.03.2017.
 */

public class CountBmiTest {

    @Test
    public void massUnder0IsInvalid() {
        //GIVEN
        float testMass = -1f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isMassValid(testMass);
        Assert.assertFalse(actual);
    }

    @Test
    public void minMassIsValid() {
        //GIVEN
        float testMass = 10f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isMassValid(testMass);
        Assert.assertTrue(actual);
    }
    @Test
    public void maxMassIsValid() {
        //GIVEN
        float testMass = 250f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isMassValid(testMass);
        Assert.assertTrue(actual);
    }

    @Test
    public void massEqualTo0IsInvalid() {
        //GIVEN
        float testMass = -0f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isMassValid(testMass);
        Assert.assertFalse(actual);
    }

    @Test
    public void massAboveMaxIsInvalid() {
        //GIVEN
        float testMass = 300f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isMassValid(testMass);
        Assert.assertFalse(actual);
    }
    @Test
    public void massExpectedValueIsValid() {
        //GIVEN
        float testMass = 63.5f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isMassValid(testMass);
        Assert.assertTrue(actual);
    }

    @Test
    public void heightAboveMaxIsInvalid() {
        //GIVEN
        float testHeight = 3.0f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isHeightValid(testHeight);
        Assert.assertFalse(actual);
    }
    @Test
    public void minHeightIsValid() {
        //GIVEN
        float testHeight = 0.5f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }
    @Test
    public void maxHeightIsValid() {
        //GIVEN
        float testHeight = 2.5f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }

    @Test
    public void heightUnder0IsInvalid() {
        //GIVEN
        float testHeight = -1.0f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isHeightValid(testHeight);
        Assert.assertFalse(actual);
    }
    @Test
    public void heightExpectedValueIsValid() {
        //GIVEN
        float testHeight = 1.75f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        boolean actual = cb.isHeightValid(testHeight);
        Assert.assertTrue(actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void countBmiThrowException() {
        //GIVEN
        float testHeight = 3.75f;
        float testMass = -1;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        float bmi = cb.countBMI(testMass,testHeight);
    }
    @Test
    public void resultOfCountBmiIsValid() {
        //GIVEN
        float testHeight = 1.75f;
        float testMass = 63;
        float delta = 0.01f;
        float expected = 20.57f;
        //WHEN
        ICountBmi cb = new CountBmiForKgM();
        //THEN
        float bmi = cb.countBMI(testMass,testHeight);
        Assert.assertEquals(expected,bmi,delta);
    }


}