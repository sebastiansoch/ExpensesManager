package com.gmail.sebastiansoch.expensemanager.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PurchaseGroup implements Parcelable {
    private String name;

    public PurchaseGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static final Creator<PurchaseGroup> CREATOR = new Creator<PurchaseGroup>() {
        @Override
        public PurchaseGroup createFromParcel(Parcel parcel) {
            return new PurchaseGroup(parcel);
        }

        @Override
        public PurchaseGroup[] newArray(int size) {
            return new PurchaseGroup[size];
        }
    };

    protected PurchaseGroup(Parcel parcel) {
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
