package com.example.hcd_fresher048.appdemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by HCD-Fresher048 on 1/11/2017.
 */

public class News implements Parcelable{
    private String content;
    private int id;

    public News(String content, int id) {
        this.content = content;
        this.id = id;
    }

    protected News(Parcel in) {
        content = in.readString();
        id = in.readInt();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeInt(id);
    }

}

class NewsData {
    public static ArrayList<News> getData() {
        ArrayList<News> newsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            newsList.add(new News("Content " + i, i));
        }
        return newsList;
    }
}

