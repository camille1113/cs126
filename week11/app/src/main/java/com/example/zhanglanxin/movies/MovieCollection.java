package com.example.zhanglanxin.movies;

/**
 * Created by zhanglanxin on 3/27/17.
 */

public class MovieCollection {
    private int page;
    private Movie[] results;
    private int total_results;
    private int total_pages;
    public String MovieTitle = " ";
    public String genreMovieTitle = " ";
    public String voteAveMovieTitle = " ";
    public String popularityMovieTitle = " ";

    public Movie[] getResult() {

        return results;
    }

    public int getPage() {

        return page;
    }

    public int getTotal_pages() {

        return total_pages;
    }

    public int getTotal_results() {

        return total_results;
    }
}
