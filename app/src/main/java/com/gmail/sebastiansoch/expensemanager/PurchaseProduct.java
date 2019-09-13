package com.gmail.sebastiansoch.expensemanager;

import android.os.Parcel;
import android.os.Parcelable;

public class PurchaseProduct implements Parcelable {

    private String name;

    public PurchaseProduct(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected PurchaseProduct(Parcel parcel) {
        this.name = parcel.readString();
    }

    public static final Creator<PurchaseProduct> CREATOR = new Creator<PurchaseProduct>() {
        @Override
        public PurchaseProduct createFromParcel(Parcel parcel) {
            return new PurchaseProduct(parcel);
        }

        @Override
        public PurchaseProduct[] newArray(int size) {
            return new PurchaseProduct[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.name);
    }
}
