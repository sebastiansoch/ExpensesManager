package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcelable;

import com.gmail.sebastiansoch.expensemanager.CategoryTiles;

import java.util.List;

public interface ExpenseManagerRepo extends Parcelable {
    List<CategoryTiles> getCategoryTilesInfo();
}
