package com.stala.grzegorz.bmicalculator;

/**
 * Created by Grzegorz on 22.03.2017.
 */

public class CountBmiForKgM implements ICountBmi {

    static final float MIN_MASS = 10;
    static final float MAX_MASS = 250;
    static final float MIN_HEIGHT = 0.5f;
    static final float MAX_HEIGHT = 2.5f;

    @Override
    public boolean isMassValid(float mass) {
        return mass <= MAX_MASS && mass >= MIN_MASS ? true : false;
    }

    @Override
    public boolean isHeightValid(float height) {
        return height <= MAX_HEIGHT && height >= MIN_HEIGHT ? true : false;

    }

    @Override
    public float countBMI(float mass, float height) {
        if (isHeightValid(height) && isMassValid(mass)) {
            float bmi = mass / (height * height);
            float round_result = (float) ((Math.floor(100*bmi))/100);
            return round_result;
        } else throw new IllegalArgumentException("schudnij, przytyj ");
    }
}
