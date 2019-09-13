package com.gmail.sebastiansoch.expensemanager;

import android.os.Parcel;
import android.os.Parcelable;


public class CategoryTiles implements Parcelable {

    private PurchaseCategory purchaseCategory;
    private String tilesTag;
    private String tilesIconPath;

    public CategoryTiles(PurchaseCategory purchaseCategory, String tilesTag, String tilesIconPath) {
        this.purchaseCategory = purchaseCategory;
        this.tilesTag = tilesTag;
        this.tilesIconPath = tilesIconPath;
    }

    public PurchaseCategory getPurchaseCategory() {
        return purchaseCategory;
    }

    public String getTilesTag() {
        return tilesTag;
    }

    public String getTilesIconName() {
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
        purchaseCategory = parcel.readParcelable(PurchaseCategory.class.getClassLoader());
        tilesTag = parcel.readString();
        tilesIconPath = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.purchaseCategory, flags);
        parcel.writeString(this.tilesTag);
        parcel.writeString(this.tilesIconPath);
    }
}
