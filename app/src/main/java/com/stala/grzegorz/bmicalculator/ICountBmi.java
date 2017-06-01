package com.stala.grzegorz.bmicalculator;

/**
 * Created by Grzegorz on 22.03.2017.
 */

public interface ICountBmi {

    public static final double BMI_POINTER_1 = 16;
    public static final double BMI_POINTER_2 = 17;
    public static final double BMI_POINTER_3 = 18.5;
    public static final double BMI_POINTER_4 = 25;
    public static final double BMI_POINTER_5 = 30;
    public static final double BMI_POINTER_6 = 35;
    public static final double BMI_POINTER_7 = 40;

    boolean isMassValid(float mass);
    boolean isHeightValid(float height);
    float countBMI(float mass, float height);

}
