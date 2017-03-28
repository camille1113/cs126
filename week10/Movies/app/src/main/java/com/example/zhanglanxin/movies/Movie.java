package com.example.zhanglanxin.movies;

/**
 * Created by zhanglanxin on 3/27/17.
 */

public class Movie {
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private int[] genre_ids;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;

    //generate getters for each private member variable

    public String getPoster_path() {

        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public double getVote_average() {

        return vote_average;
    }

    public boolean isVideo() {

        return video;
    }

    public int getVote_count() {

        return vote_count;
    }

    public double getPopularity() {

        return popularity;
    }

    public String getBackdrop_path() {

        return backdrop_path;
    }

    public String getTitle() {

        return title;
    }

    public String getOriginal_language() {

        return original_language;
    }

    public String getOriginal_title() {

        return original_title;
    }

    public int getId() {

        return id;
    }

    public int[] getGenre_ids() {

        return genre_ids;
    }

    public String getRelease_date() {

        return release_date;
    }

    public boolean isAdult() {

        return adult;
    }
}
