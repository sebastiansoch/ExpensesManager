package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcel;

import com.gmail.sebastiansoch.expensemanager.PurchaseGroup;
import com.gmail.sebastiansoch.expensemanager.PurchaseGroupTiles;
import com.gmail.sebastiansoch.expensemanager.PurchaseProduct;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManagerFakeRepo implements ExpenseManagerRepo {

    private List<PurchaseGroupTiles> purchaseGroupTilesList = new ArrayList<>();

    public ExpenseManagerFakeRepo() {
    }

    @Override
    public List<PurchaseGroupTiles> getPurchaseGroupTilesInfo() {
        return purchaseGroupTilesList;
    }

    @Override
    public List<PurchaseProduct> getProductsForPurchaseGroup(PurchaseGroup purchaseGroup) {
        List<PurchaseProduct> purchaseProducts = new ArrayList<>();
        switch(purchaseGroup.getName()) {
            case "FOOD":
                purchaseProducts.add(new PurchaseProduct("BREAD"));
                purchaseProducts.add(new PurchaseProduct("EGGS"));
                purchaseProducts.add(new PurchaseProduct("APPLE"));
                purchaseProducts.add(new PurchaseProduct("LETTUCE"));
                purchaseProducts.add(new PurchaseProduct("BUTTER"));
                break;
            case "CLEANING SUPPLIES":
                purchaseProducts.add(new PurchaseProduct("DOMESTOS"));
                purchaseProducts.add(new PurchaseProduct("WASHING POWDER"));
                purchaseProducts.add(new PurchaseProduct("WASHING LIQUID"));
                break;
            case "TRANSPORTATION":
                purchaseProducts.add(new PurchaseProduct("BUS TICKET"));
                purchaseProducts.add(new PurchaseProduct("TRAIN TICKET"));
                break;
            case "CLOTHES":
                purchaseProducts.add(new PurchaseProduct("T-SHIRT"));
                purchaseProducts.add(new PurchaseProduct("SHIRT"));
                purchaseProducts.add(new PurchaseProduct("TROUSERS"));
                break;
            case "GIFTS":
                purchaseProducts.add(new PurchaseProduct("FAMILY"));
                purchaseProducts.add(new PurchaseProduct("FRIENDS"));
                break;
        }

        return purchaseProducts;
    }

    public void init() {
        preparePurchaseGroupTilesInfo();
    }

    private void preparePurchaseGroupTilesInfo() {
        purchaseGroupTilesList.add(new PurchaseGroupTiles(new PurchaseGroup("FOOD"), "food", "ic_settings_black_24dp"));
        purchaseGroupTilesList.add(new PurchaseGroupTiles(new PurchaseGroup("CLEANING SUPPLIES"), "cleaning_supplies", "ic_settings_black_24dp"));
        purchaseGroupTilesList.add(new PurchaseGroupTiles(new PurchaseGroup("TRANSPORTATION"), "transportation", "ic_settings_black_24dp"));
        purchaseGroupTilesList.add(new PurchaseGroupTiles(new PurchaseGroup("CLOTHES"), "clothes", "ic_settings_black_24dp"));
        purchaseGroupTilesList.add(new PurchaseGroupTiles(new PurchaseGroup("GIFTS"), "gifts", "ic_settings_black_24dp"));
    }

    public static final Creator<ExpenseManagerFakeRepo> CREATOR = new Creator<ExpenseManagerFakeRepo>() {
        @Override
        public ExpenseManagerFakeRepo createFromParcel(Parcel parcel) {
            return new ExpenseManagerFakeRepo(parcel);
        }

        @Override
        public ExpenseManagerFakeRepo[] newArray(int size) {
            return new ExpenseManagerFakeRepo[size];
        }
    };

    public ExpenseManagerFakeRepo(Parcel parcel) {
        this.purchaseGroupTilesList = parcel.readArrayList(PurchaseGroupTiles.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.purchaseGroupTilesList);
    }
}
