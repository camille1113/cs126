package com.example.zhanglanxin.password;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 3/14/17.
 */
public class PasswordGeneratorTest {
    String test1 = PasswordGenerator.generate(3,7,4);
    String test2 = PasswordGenerator.generate(5,10,15);
    String test3 = PasswordGenerator.generate(1,1,-1);

    @Test
    public void testStringSize() throws Exception {
        assertEquals(14,test1.length());
        assertEquals(30,test2.length());
        assertEquals("Incorrect length",test3);
    }
    @Test
    public void testNumberSize() throws Exception{
        int nums1 = 0;
        for(int i = 0; i < test1.length(); i++){
            if(Character.isDigit(test1.charAt(i)))
                nums1++;
        }
        int nums2 = 0;
        for(int i = 0; i < test2.length(); i++){
            if(Character.isDigit(test2.charAt(i)))
                nums2++;
        }
        assertEquals(3,nums1);
        assertEquals(5,nums2);
    }
    @Test
    public void testSymbolSize() throws Exception{
        int nums1 = 0;
        for(int i = 0; i < test1.length(); i++){
            if(Character.isDigit(test1.charAt(i))||Character.isLetter(test1.charAt(i)))
                nums1++;
        }
        nums1 = 14-nums1;
        int nums2 = 0;
        for(int i = 0; i < test2.length(); i++){
            if(Character.isDigit(test2.charAt(i))||Character.isLetter(test2.charAt(i)))
                nums2++;
        }
        nums2 = 30-nums2;
        assertEquals(7,nums1);
        assertEquals(10,nums2);

    }
    @Test
    public void testLetterSize() throws Exception{
        int nums1 = 0;
        for(int i = 0; i < test1.length(); i++){
            if(Character.isLetter(test1.charAt(i)))
                nums1++;
        }
        int nums2 = 0;
        for(int i = 0; i < test2.length(); i++){
            if(Character.isLetter(test2.charAt(i)))
                nums2++;
        }
        assertEquals(4,nums1);
        assertEquals(15,nums2);

    }



    @Test
    public void testShuffle() throws Exception {
        assertFalse(PasswordGenerator.shuffle(test1)==test1);
        assertFalse(PasswordGenerator.shuffle(test2)==test2);
        assertFalse(PasswordGenerator.shuffle(test3)==test3);
    }

}