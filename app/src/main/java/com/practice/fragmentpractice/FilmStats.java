package com.practice.fragmentpractice;

import android.support.annotation.Nullable;

public class FilmStats {

    private String name;
    private Genres[] genres;
    private String runTime;


    private int metacriticsScore;
    private String posterImgUrl;
    private String ageRating;

    public FilmStats(String name, Genres[] genres, String runTime, int metacriticsScore, String posterImgUrl, String ageRating) {
        this.name = name;
        this.genres = genres;
        this.runTime = runTime;
        this.metacriticsScore = metacriticsScore;
        this.posterImgUrl = posterImgUrl;
        this.ageRating = ageRating;
    }

    public String getName() {
        return name;
    }

    public Genres[] getGenres() {
        return genres;
    }

    public String getRunTime() {
        return runTime;
    }

    public int getMetacriticsScore() {
        return metacriticsScore;
    }

    public String getPosterImgUrl() {
        return posterImgUrl;
    }

    public String getAgeRating(){
        return ageRating;
    }
}

