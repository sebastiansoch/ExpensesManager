package com.gmail.sebastiansoch.expensemanager.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupTilesInfo;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;
import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.*;


public class PurchaseDAO {

    private SQLiteDatabase database;

    public PurchaseDAO(DBHelper DBHelper) {
        database = DBHelper.getWritableDatabase();
    }

    /*
        List<PurchaseGroupTiles> getPurchaseGroupTilesInfo();
    List<SchemaCategory> getPurchaseCategoriesForGroup(SchemaCategoryGroup purchaseGroup);
     */

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
}
