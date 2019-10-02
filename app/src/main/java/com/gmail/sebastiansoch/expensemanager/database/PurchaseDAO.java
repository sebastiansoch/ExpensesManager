package com.gmail.sebastiansoch.expensemanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.model.PurchaseGroupTilesInfo;
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
    List<PurchaseCategory> getPurchaseCategoriesForGroup(PurchaseGroup purchaseGroup);
     */

    public List<PurchaseGroupTilesInfo> loadAllPurchaseGroupTileInfo() {
        StringBuilderWrapper sqlBuilder = new StringBuilderWrapper("SELECT");
        sqlBuilder.append(PurchaseGroup.COLUMN_NAME + ",");
        sqlBuilder.append(PurchaseGroup.COLUMN_TAG + ",");
        sqlBuilder.append(Tiles.COLUMN_PATH);
        sqlBuilder.append("FROM").append(PurchaseGroup.TABLE_NAME + " pg");
        sqlBuilder.append("LEFT JOIN").append(Tiles.TABLE_NAME + " ti");
        sqlBuilder.append("ON").append("pg." + PurchaseGroup.COLUMN_TILES_ID).append("=").append("ti." + Tiles.COLUMN_ID);

        Cursor cursor = database.rawQuery(sqlBuilder.toString(), null);
        cursor.moveToFirst();

        List<PurchaseGroupTilesInfo> purchaseGroupTilesInfo = new ArrayList<>();
        while (cursor.moveToNext()) {
            String purchaseGroupName = cursor.getString(cursor.getColumnIndex(PurchaseGroup.COLUMN_NAME));
            String group_tag = cursor.getString(cursor.getColumnIndex(PurchaseGroup.COLUMN_TAG));
            String iconPath = cursor.getString(cursor.getColumnIndex(Tiles.COLUMN_PATH));
            purchaseGroupTilesInfo.add(new PurchaseGroupTilesInfo(purchaseGroupName, group_tag, iconPath));
        }

        return purchaseGroupTilesInfo;
   }
}
