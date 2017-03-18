package com.example.zhanglanxin.password;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * this class is used to generate a random password
 * Created by zhanglanxin on 3/14/17.
 */

public class PasswordGenerator {

    /**
     * @param numbers the length of numbers in the password
     * @param symbols the length of symbols in the password
     * @param letters the length of letters in the password
     * @return a string combined with numbers symbols and letters
     */
    public static String generate(int numbers, int symbols, int letters) {
        if (letters < 0)
            return "Incorrect length";
        if (numbers + symbols + letters > 100)
            return "password too long!";
        Random random = new Random();
        List<Character> password = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        char tempNumber;
        for (int i = 0; i < numbers; i++) {
            tempNumber = (char)(random.nextInt(9) + 48);//numbers in ascii is from 48-57
            password.add(tempNumber);
        }
        char tempChar;
        for (int i = 0; i < symbols; i++) {
            tempChar = (char) (random.nextInt(14) + 33);//symbols in ascii from 33-47
            password.add(tempChar);
        }
        char tempLetter;
        for (int i = 0; i < letters; i++) {
            if (random.nextInt(2) == 0) {
                tempLetter = (char) (random.nextInt(26) + 65);//uppercase letters in ascii is from 65-91
            } else {
                tempLetter = (char) (random.nextInt(26) + 97);//lowercase letters in ascii is from 97-122
            }
            password.add(tempLetter);
        }
        Collections.shuffle(password);
        for (Character c : password) {
            result.append(c);
        }
        return result.toString();
    }
}


