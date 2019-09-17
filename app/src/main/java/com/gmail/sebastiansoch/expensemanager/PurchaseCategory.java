package com.gmail.sebastiansoch.expensemanager;

import android.os.Parcel;
import android.os.Parcelable;

public class PurchaseCategory implements Parcelable {
    private String name;

    public PurchaseCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static final Creator<PurchaseCategory> CREATOR = new Creator<PurchaseCategory>() {
        @Override
        public PurchaseCategory createFromParcel(Parcel parcel) {
            return new PurchaseCategory(parcel);
        }

        @Override
        public PurchaseCategory[] newArray(int size) {
            return new PurchaseCategory[size];
        }
    };

    protected PurchaseCategory(Parcel parcel) {
        this.name = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.name);
    }

}
