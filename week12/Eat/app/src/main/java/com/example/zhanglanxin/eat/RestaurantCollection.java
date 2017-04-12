package com.example.zhanglanxin.eat;

/**
 * Created by zhanglanxin on 4/11/17.
 */

public class RestaurantCollection {
    private String next_page_token;
    private Restaurant[] results;
    public Restaurant[] getResults(){
        return results;
    }
}
