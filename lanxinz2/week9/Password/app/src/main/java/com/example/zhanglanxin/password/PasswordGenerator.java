package com.example.zhanglanxin.password;

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
     *
     * @param numbers the length of numbers in the password
     * @param symbols
     * @param letters
     * @return a string combined with numbers symbols and letters
     */
    public static String generate(int numbers, int symbols, int letters){
        if(letters<0)
            return "Incorrect length";
        Random random = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int tempNumber;
        for (int i = 0; i < numbers; i++){
            tempNumber = random.nextInt(9);//generate number from 0-9
            randomStringBuilder.append(tempNumber);
        }
        char tempChar;
        for (int i = 0; i < symbols; i++){
            tempChar = (char) (random.nextInt(14)+33);//symbols in ascii
            randomStringBuilder.append(tempChar);
        }
        char tempLetter;
        for (int i = 0; i < letters; i++){
            if(random.nextInt(2)==0){
                tempLetter = (char)(random.nextInt(26)+65);//uppercase letters in ascii
            }
            else{
                tempLetter = (char)(random.nextInt(26)+97);//lowercase letters in ascii
            }
            randomStringBuilder.append(tempLetter);
        }
        String myString = String.valueOf(randomStringBuilder.toString());
        return shuffle(myString);
    }

    /**
     *  this method rearranges a string
     * @param input string
     * @return a shuffled string
     */
    public static String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder shuffled = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            shuffled.append(characters.remove(randPicker));
        }
        return shuffled.toString();
    }
}
