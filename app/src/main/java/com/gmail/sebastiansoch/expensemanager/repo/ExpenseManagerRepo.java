package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcelable;

import com.gmail.sebastiansoch.expensemanager.data.PurchaseCategory;
import com.gmail.sebastiansoch.expensemanager.data.PurchaseGroup;
import com.gmail.sebastiansoch.expensemanager.data.PurchaseGroupTiles;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoriesInfoForSettings;

import java.util.List;

public interface ExpenseManagerRepo extends Parcelable {
    List<PurchaseGroupTiles> getPurchaseGroupTilesInfo();
    List<PurchaseCategory> getPurchaseCategoriesForGroup(PurchaseGroup purchaseGroup);

    List<CategoriesInfoForSettings> getAllCategoriesInfoForSettings();
}
