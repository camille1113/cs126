package com.example.zhanglanxin.password;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 3/14/17.
 */
public class PasswordGeneratorTest {
    String test1 = PasswordGenerator.generate(1,1,1);
    String test2 = PasswordGenerator.generate(1,2,3);
    String test3 = PasswordGenerator.generate(1,1,-1);

    @Test
    public void testGenerate() throws Exception {
        assertEquals(3,test1.length());
        assertEquals(6,test2.length());
        assertEquals("Incorrect length",test3);
    }

    @Test
    public void testShuffle() throws Exception {
        assertFalse(PasswordGenerator.shuffle(test1)==test1);
        assertFalse(PasswordGenerator.shuffle(test2)==test2);
        assertFalse(PasswordGenerator.shuffle(test3)==test3);
    }

}