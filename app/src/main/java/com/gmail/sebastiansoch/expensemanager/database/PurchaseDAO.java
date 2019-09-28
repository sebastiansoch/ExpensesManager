package com.gmail.sebastiansoch.expensemanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.model.PurchaseGroupTilesInfo;
import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.sebastiansoch.expensemanager.database.PurchaseDBSchema.PurchaseGroup;
import static com.gmail.sebastiansoch.expensemanager.database.PurchaseDBSchema.Tiles;

public class PurchaseDAO {

    private SQLiteDatabase database;

    public PurchaseDAO(PurchaseDBHelper purchaseDBHelper) {
        database = purchaseDBHelper.getWritableDatabase();
    }

    /*
        List<PurchaseGroupTiles> getPurchaseGroupTilesInfo();
    List<PurchaseCategory> getPurchaseCategoriesForGroup(PurchaseGroup purchaseGroup);
     */

    public List<PurchaseGroupTilesInfo> loadAllPurchaseGroupTileInfo() {
        StringBuilderWrapper sqlBuilder = new StringBuilderWrapper("SELECT");
        sqlBuilder.append("pg.name, pg.tag, gt.path");
        sqlBuilder.append("FROM purchase_group pg");
        sqlBuilder.append("LEFT JOIN purchase_group_tiles gt");
        sqlBuilder.append("ON pg.tiles_id = gt.id");

        Cursor cursor = database.rawQuery(sqlBuilder.toString(), null);
        cursor.moveToFirst();

        List<PurchaseGroupTilesInfo> purchaseGroupTilesInfos = new ArrayList<>();
        while (cursor.moveToNext()) {
            String purchaseGroupName = cursor.getString(cursor.getColumnIndex("pg.name"));
            String group_tag = cursor.getString(cursor.getColumnIndex("pg.tag"));
            String iconPath = cursor.getString(cursor.getColumnIndex("gt.path"));
            purchaseGroupTilesInfos.add(new PurchaseGroupTilesInfo(purchaseGroupName, group_tag, iconPath));
        }

        return purchaseGroupTilesInfos;
    }

    public void createPurchaseGroups() {
        ContentValues tagValues = new ContentValues();
        tagValues.put(Tiles.COLUMN_PATH, "ic_settings_black_24dp");
        database.insert(Tiles.TABLE_NAME, null, tagValues);

        ContentValues values1 = new ContentValues();
        values1.put(PurchaseGroup.COLUMN_NAME, "DOM");
        values1.put(PurchaseGroup.COLUMN_TAG, "house");
        values1.put(PurchaseGroup.COLUMN_TILES_ID, "0");
        database.insert(PurchaseGroup.TABLE_NAME, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put(PurchaseGroup.COLUMN_NAME, "ROZRYWKA");
        values2.put(PurchaseGroup.COLUMN_TAG, "entertainment");
        values2.put(PurchaseGroup.COLUMN_TILES_ID, "0");
        database.insert(PurchaseGroup.TABLE_NAME, null, values2);
    }

}
