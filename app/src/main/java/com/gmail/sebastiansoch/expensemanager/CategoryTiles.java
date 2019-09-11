package com.gmail.sebastiansoch.expensemanager;

import android.os.Parcel;
import android.os.Parcelable;


public class CategoryTiles implements Parcelable {

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


    public static final Creator<CategoryTiles> CREATOR = new Creator<CategoryTiles>() {
        @Override
        public CategoryTiles createFromParcel(Parcel parcel) {
            return new CategoryTiles(parcel);
        }

        @Override
        public CategoryTiles[] newArray(int size) {
            return new CategoryTiles[size];
        }
    };

    public CategoryTiles(Parcel parcel) {
        this.categoryName = parcel.readString();
        this.tilesTag = parcel.readString();
        this.tilesIconPath = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.categoryName);
        parcel.writeString(this.tilesTag);
        parcel.writeString(this.tilesIconPath);
    }
}
