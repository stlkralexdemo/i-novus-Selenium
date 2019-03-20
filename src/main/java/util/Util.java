package util;

import java.util.*;

public class Util {
    public static int rand(int max) {
        Random random = new Random();
        int i = random.nextInt(max - 1);
        return i;
    }

    public static int parseNum(String price) {
        String[] numbers = price.split("");
        String numberNew = "";
        for (String number : numbers) {
            if (number.equals(" ")){

            } else numberNew += number;
        }

        return Integer.parseInt(numberNew);


    }
}
