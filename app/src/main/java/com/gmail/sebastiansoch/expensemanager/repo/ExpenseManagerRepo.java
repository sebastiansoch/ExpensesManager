package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcelable;

import com.gmail.sebastiansoch.expensemanager.PurchaseGroup;
import com.gmail.sebastiansoch.expensemanager.PurchaseGroupTiles;
import com.gmail.sebastiansoch.expensemanager.PurchaseProduct;

import java.util.List;

public interface ExpenseManagerRepo extends Parcelable {
    List<PurchaseGroupTiles> getPurchaseGroupTilesInfo();
    List<PurchaseProduct> getProductsForPurchaseGroup(PurchaseGroup purchaseGroup);
}
