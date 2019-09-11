package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcel;
import android.os.Parcelable;

import com.gmail.sebastiansoch.expensemanager.CategoryTiles;


import java.util.ArrayList;
import java.util.List;

public class ExpenseManagerFakeRepo implements ExpenseManagerRepo {

    private static final String ICON_RESOURCE = "com.gmail.sebastiansoch.expensemanager.R.drawable.";
    private List<CategoryTiles> categoryTilesList = new ArrayList<>();

    public ExpenseManagerFakeRepo() {
    }

    public void init() {
        prepareCategoryTilesInfo();
    }

    @Override
    public List<CategoryTiles> getCategoryTilesInfo() {
        return categoryTilesList;
    }

    private void prepareCategoryTilesInfo() {
        String tilesIconPath = ICON_RESOURCE + "ic_settings_black_24dp";

        String category_1 = "Mieszkanie";
        String tag_1 = "house";
        categoryTilesList.add(new CategoryTiles(category_1, tag_1, tilesIconPath));

        String category_2 = "Jedzenie";
        String tag_2 = "food";
        categoryTilesList.add(new CategoryTiles(category_2, tag_2, tilesIconPath));

        String category_3 = "Transport";
        String tag_3 = "transportation";
        categoryTilesList.add(new CategoryTiles(category_3, tag_3, tilesIconPath));

        String category_4 = "Środki czystości";
        String tag_4 = "cleaning_supplies";
        categoryTilesList.add(new CategoryTiles(category_4, tag_4, tilesIconPath));

        String category_5 = "Ubezpieczenie";
        String tag_5 = "insurance";
        categoryTilesList.add(new CategoryTiles(category_5, tag_5, tilesIconPath));
    }

    public static final Parcelable.Creator<ExpenseManagerFakeRepo> CREATOR = new Parcelable.Creator<ExpenseManagerFakeRepo>() {

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
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeList(this.categoryTilesList);
    }
}
