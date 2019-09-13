package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcel;
import android.widget.Toast;

import com.gmail.sebastiansoch.expensemanager.CategoryTiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManagerFakeRepo implements ExpenseManagerRepo {

    private List<CategoryTiles> categoryTilesList = new ArrayList<>();

    public ExpenseManagerFakeRepo() {
    }

    @Override
    public List<CategoryTiles> getCategoryTilesInfo() {
        return categoryTilesList;
    }

    public void init() {
        prepareCategoryTilesInfo();
    }

    private void prepareCategoryTilesInfo() {
        categoryTilesList.add(new CategoryTiles("Żywność", "food", "ic_settings_black_24dp"));
        categoryTilesList.add(new CategoryTiles("Środki czystości", "cleaning_supplies", "ic_settings_black_24dp"));
        categoryTilesList.add(new CategoryTiles("Transport", "transportation", "ic_settings_black_24dp"));
        categoryTilesList.add(new CategoryTiles("Ubrania", "clothes", "ic_settings_black_24dp"));
        categoryTilesList.add(new CategoryTiles("Prezenty", "gifts", "ic_settings_black_24dp"));
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
        this.categoryTilesList = parcel.readArrayList(CategoryTiles.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.categoryTilesList);
    }
}
