package com.example.android.movieapp.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie implements Parcelable {

    private int id;
    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private String originLanguage;
    private String originTitle;
    private String releaseDate;
    private Double popularityRate;
    private List<Integer> genreIds;
    private int voteCount;
    private int voteAverage;
    private Boolean isAdult;
    private Boolean isVideo;


    public Movie(int id, String title, String overview, String releaseDate, String posterPath, String backdropPath, String originLanguage, String originTitle, Double popularityRate, List<Integer> genreIds, int voteCount, int voteAverage, boolean isAdult, boolean isVideo) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.originLanguage = originLanguage;
        this.originTitle = originTitle;
        this.popularityRate = popularityRate;
        this.genreIds = genreIds;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.isAdult = isAdult;
        this.isVideo = isVideo;
    }

    private Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        originLanguage = in.readString();
        originTitle = in.readString();
        popularityRate = in.readDouble();
        this.genreIds = new ArrayList<>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        voteCount = in.readInt();
        voteAverage = in.readInt();
        isAdult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        isVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginLanguage() {
        return originLanguage;
    }

    public void setOriginLanguage(String originLanguage) {
        this.originLanguage = originLanguage;
    }

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle;
    }

    public Double getPopularityRate() {
        return popularityRate;
    }

    public void setPopularityRate(Double popularityRate) {
        this.popularityRate = popularityRate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(originLanguage);
        parcel.writeString(originTitle);
        parcel.writeDouble(popularityRate);
        parcel.writeList(genreIds);
        parcel.writeInt(voteCount);
        parcel.writeInt(voteAverage);
        parcel.writeValue(isAdult);
        parcel.writeValue(isVideo);

    }


    public final static Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }

    };
}
