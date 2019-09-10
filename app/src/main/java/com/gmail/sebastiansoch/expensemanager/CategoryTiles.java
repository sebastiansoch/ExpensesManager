package com.gmail.sebastiansoch.expensemanager;

import android.os.Parcel;
import android.os.Parcelable;


public class CategoryTiles implements Parcelable {

    public static final Creator<CategoryTiles> CREATOR = new Creator<CategoryTiles>() {
        @Override
        public CategoryTiles createFromParcel(Parcel in) {
            return new CategoryTiles(in);
        }

        @Override
        public CategoryTiles[] newArray(int size) {
            return new CategoryTiles[size];
        }
    };

    private String categoryName;
    private String tilesTag;
    private String tilesIconPath;

    public CategoryTiles(String categoryName, String tilesTag, String tilesIconPath) {
        this.categoryName = categoryName;
        this.tilesTag = tilesTag;
        this.tilesIconPath = tilesIconPath;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getTilesTag() {
        return tilesTag;
    }

    public String getTilesIconPath() {
        return tilesIconPath;
    }

    public CategoryTiles(Parcel in) {
        categoryName = in.readString();
        tilesTag = in.readString();
        tilesIconPath = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.categoryName);
        parcel.writeString(this.tilesTag);
        parcel.writeString(this.tilesIconPath);
    }
}
