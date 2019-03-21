package util;

import java.util.*;


public class Util {
    /**
     * Метод для вычисления рандомного числа от 0 и до количества товаров на странице пластинок
     */
    public static int rand(int max) {

        Random random = new Random();
        return random.nextInt(max - 1);
    }

    /**
     * Метод для перевода цены из строки в число, с удалением лишних пробелов, если имеются
     */
    public static int parseNum(String price) {

        String[] numbers = price.split("");
        String numberNew = "";
        for (String number : numbers) {
            if (!number.equals(" ")) {
                numberNew += number;
            }
        }
        return Integer.parseInt(numberNew);
    }
}
