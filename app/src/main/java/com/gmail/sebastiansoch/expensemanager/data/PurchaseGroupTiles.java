package com.gmail.sebastiansoch.expensemanager.data;

import android.os.Parcel;
import android.os.Parcelable;


public class PurchaseGroupTiles implements Parcelable {

    private PurchaseGroup purchaseGroup;
    private String tilesTag;
    private String tilesIconPath;

    public PurchaseGroupTiles(PurchaseGroup purchaseGroup, String tilesTag, String tilesIconPath) {
        this.purchaseGroup = purchaseGroup;
        this.tilesTag = tilesTag;
        this.tilesIconPath = tilesIconPath;
    }

    public PurchaseGroup getPurchaseGroup() {
        return purchaseGroup;
    }

    public String getTilesTag() {
        return tilesTag;
    }

    public String getTilesIconName() {
        return tilesIconPath;
    }


    public static final Creator<PurchaseGroupTiles> CREATOR = new Creator<PurchaseGroupTiles>() {
        @Override
        public PurchaseGroupTiles createFromParcel(Parcel parcel) {
            return new PurchaseGroupTiles(parcel);
        }

        @Override
        public PurchaseGroupTiles[] newArray(int size) {
            return new PurchaseGroupTiles[size];
        }
    };

    public PurchaseGroupTiles(Parcel parcel) {
        purchaseGroup = parcel.readParcelable(PurchaseGroup.class.getClassLoader());
        tilesTag = parcel.readString();
        tilesIconPath = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.purchaseGroup, flags);
        parcel.writeString(this.tilesTag);
        parcel.writeString(this.tilesIconPath);
    }
}
