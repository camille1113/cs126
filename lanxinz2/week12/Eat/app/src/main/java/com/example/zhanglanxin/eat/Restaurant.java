package com.example.zhanglanxin.eat;

/**
 * Created by zhanglanxin on 4/11/17.
 */

public class Restaurant {
    private String formatted_address;
    private String name;
    private String rating;
    private Opening_hours opening_hours;
    private Photo photo;
    public String getFormatted_address() {
        return formatted_address;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public Opening_hours getOpening_hours() {
        return opening_hours;
    }

    public Photo getPhoto(){
        return photo;
    }

}
