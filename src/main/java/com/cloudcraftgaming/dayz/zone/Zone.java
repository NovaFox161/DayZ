package com.cloudcraftgaming.dayz.zone;

/**
 * Created by Nova Fox on 12/3/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: DayZ
 */
@SuppressWarnings("unused")
public enum Zone {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);


    private int val;
    Zone(int value) {
        val = value;
    }

    //Getters
    public int getValue() {
        return val;
    }

    public static Zone fromValue(int value) {
        if (value == 1) {
            return ONE;
        } else if (value == 2) {
            return TWO;
        } else if (value == 3) {
            return THREE;
        } else if (value == 4) {
            return FOUR;
        } else if (value == 5) {
            return FIVE;
        } else if (value == 6) {
            return SIX;
        } else {
            return ONE;
        }
    }

    public static Boolean isValid(int value) {
        return value > 0 && value < 7;
    }
}