package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcelable;

import com.gmail.sebastiansoch.expensemanager.PurchaseCategory;
import com.gmail.sebastiansoch.expensemanager.PurchaseGroup;
import com.gmail.sebastiansoch.expensemanager.PurchaseGroupTiles;

import java.util.List;

public interface ExpenseManagerRepo extends Parcelable {
    List<PurchaseGroupTiles> getPurchaseGroupTilesInfo();
    List<PurchaseCategory> getPurchaseCategoriesForGroup(PurchaseGroup purchaseGroup);
}
