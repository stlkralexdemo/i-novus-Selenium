package pages;

import java.util.Random;

public class Util {
    public static int rand(int max){
        Random random = new Random();
        int i = random.nextInt(max - 1);
        return i;
    }
}
