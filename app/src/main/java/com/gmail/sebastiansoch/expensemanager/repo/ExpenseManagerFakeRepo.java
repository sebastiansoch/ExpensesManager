package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcel;

import com.gmail.sebastiansoch.expensemanager.data.PurchaseCategory;
import com.gmail.sebastiansoch.expensemanager.data.PurchaseGroup;
import com.gmail.sebastiansoch.expensemanager.data.PurchaseGroupTiles;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoriesInfoForSettings;

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
    public List<PurchaseCategory> getPurchaseCategoriesForGroup(PurchaseGroup purchaseGroup) {
        List<PurchaseCategory> purchaseCategories = new ArrayList<>();
        switch(purchaseGroup.getName()) {
            case "FOOD":
                purchaseCategories.add(new PurchaseCategory("BREAD"));
                purchaseCategories.add(new PurchaseCategory("EGGS"));
                purchaseCategories.add(new PurchaseCategory("APPLE"));
                purchaseCategories.add(new PurchaseCategory("LETTUCE"));
                purchaseCategories.add(new PurchaseCategory("BUTTER"));
                break;
            case "CLEANING SUPPLIES":
                purchaseCategories.add(new PurchaseCategory("DOMESTOS"));
                purchaseCategories.add(new PurchaseCategory("WASHING POWDER"));
                purchaseCategories.add(new PurchaseCategory("WASHING LIQUID"));
                break;
            case "TRANSPORTATION":
                purchaseCategories.add(new PurchaseCategory("BUS TICKET"));
                purchaseCategories.add(new PurchaseCategory("TRAIN TICKET"));
                break;
            case "CLOTHES":
                purchaseCategories.add(new PurchaseCategory("T-SHIRT"));
                purchaseCategories.add(new PurchaseCategory("SHIRT"));
                purchaseCategories.add(new PurchaseCategory("TROUSERS"));
                break;
            case "GIFTS":
                purchaseCategories.add(new PurchaseCategory("FAMILY"));
                purchaseCategories.add(new PurchaseCategory("FRIENDS"));
                break;
        }

        return purchaseCategories;
    }

    @Override
    public List<CategoriesInfoForSettings> getAllCategoriesInfoForSettings() {
        return null;
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
