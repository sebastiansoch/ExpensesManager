package com.gmail.sebastiansoch.expensemanager.repo;

import android.os.Parcelable;

import com.gmail.sebastiansoch.expensemanager.CategoryTiles;
import com.gmail.sebastiansoch.expensemanager.PurchaseCategory;
import com.gmail.sebastiansoch.expensemanager.PurchaseProduct;

import java.util.List;

public interface ExpenseManagerRepo extends Parcelable {
    List<CategoryTiles> getCategoryTilesInfo();
    List<PurchaseProduct> getProductsForCategory(PurchaseCategory purchaseCategory);
}
