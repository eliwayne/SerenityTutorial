package com.studentapp.utills;

import java.util.Random;

public class TestUtils {
    public static String getRandomValue(){
        Random random = new Random();
        int randomeInt = random.nextInt(100000);
        return Integer.toString(randomeInt);
    }
}
