package com.stala.grzegorz.bmicalculator;

/**
 * Created by Grzegorz on 25.03.2017.
 */

public class CountBmiForLbIn implements ICountBmi {

    static final float MIN_MASS = 22;
    static final float MAX_MASS = 550;
    static final float MIN_HEIGHT = 20;
    static final float MAX_HEIGHT = 98;

    @Override
    public boolean isMassValid(float mass) {
        return mass >= MIN_MASS && mass <= MAX_MASS ? true : false;
    }

    @Override
    public boolean isHeightValid(float height) {
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT ? true : false;
    }

    @Override
    public float countBMI(float mass, float height) {
        if (isHeightValid(height) && isMassValid(mass)) {
            float bmi = (mass / (height * height)) * 703;
            float round_result = (float) ((Math.floor(100 * bmi)) / 100);
            return round_result;
        } else throw new IllegalArgumentException("schudnij, przytyj ");
    }
}
