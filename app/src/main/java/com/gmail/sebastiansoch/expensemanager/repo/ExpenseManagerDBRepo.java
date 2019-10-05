package com.gmail.sebastiansoch.expensemanager.repo;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.gmail.sebastiansoch.expensemanager.data.PurchaseCategory;
import com.gmail.sebastiansoch.expensemanager.data.PurchaseGroup;
import com.gmail.sebastiansoch.expensemanager.data.PurchaseGroupTiles;
import com.gmail.sebastiansoch.expensemanager.database.PurchaseDAO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoriesInfoForSettings;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;

import java.util.List;

public class ExpenseManagerDBRepo implements ExpenseManagerRepo {
    private Context applicationContext;

    public ExpenseManagerDBRepo(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void initDAO() {
        PurchaseDAO purchaseDAO = new PurchaseDAO(new DBHelper(applicationContext));
    }

    @Override
    public List<PurchaseGroupTiles> getPurchaseGroupTilesInfo() {
        return null;
    }

    @Override
    public List<PurchaseCategory> getPurchaseCategoriesForGroup(PurchaseGroup purchaseGroup) {
        return null;
    }

    @Override
    public List<CategoriesInfoForSettings> getAllCategoriesInfoForSettings() {
        return null;
    }

    /*
        public List<CategoryGroupTilesInfo> loadAllPurchaseGroupTileInfo() {
        StringBuilderWrapper sqlBuilder = new StringBuilderWrapper("SELECT");
        sqlBuilder.append(SchemaCategoryGroup.COLUMN_NAME + ",");
        sqlBuilder.append(SchemaCategoryGroup.COLUMN_TAG + ",");
        sqlBuilder.append(SchemaTiles.COLUMN_PATH);
        sqlBuilder.append("FROM").append(SchemaCategoryGroup.TABLE_NAME + " pg");
        sqlBuilder.append("LEFT JOIN").append(SchemaTiles.TABLE_NAME + " ti");
        sqlBuilder.append("ON").append("pg." + SchemaCategoryGroup.COLUMN_TILES_ID).append("=").append("ti." + SchemaTiles.COLUMN_ID);

        Cursor cursor = database.rawQuery(sqlBuilder.toString(), null);
        cursor.moveToFirst();

        List<CategoryGroupTilesInfo> categoryGroupTilesInfo = new ArrayList<>();
        while (cursor.moveToNext()) {
            String purchaseGroupName = cursor.getString(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_NAME));
            String group_tag = cursor.getString(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_TAG));
            String iconPath = cursor.getString(cursor.getColumnIndex(SchemaTiles.COLUMN_PATH));
            categoryGroupTilesInfo.add(new CategoryGroupTilesInfo(purchaseGroupName, group_tag, iconPath));
        }

        return categoryGroupTilesInfo;
   }
     */


    public ExpenseManagerDBRepo(Parcel parcel) {
        this.applicationContext = parcel.readParcelable(Context.class.getClassLoader());
    }

    public static final Creator<ExpenseManagerDBRepo> CREATOR = new Creator<ExpenseManagerDBRepo>() {
        @Override
        public ExpenseManagerDBRepo createFromParcel(Parcel parcel) {
            return new ExpenseManagerDBRepo(parcel);
        }

        @Override
        public ExpenseManagerDBRepo[] newArray(int size) {
            return new ExpenseManagerDBRepo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable((Parcelable) this.applicationContext, flags);
    }
}
