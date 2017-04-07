package com.stala.grzegorz.bmicalculator;

/**
 * Created by Grzegorz on 22.03.2017.
 */

public interface ICountBmi {

    boolean isMassValid(float mass);
    boolean isHeightValid(float height);
    float countBMI(float mass, float height);
}
