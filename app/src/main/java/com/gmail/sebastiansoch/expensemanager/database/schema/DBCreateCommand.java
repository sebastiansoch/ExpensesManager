package com.gmail.sebastiansoch.expensemanager.database.schema;

import android.content.ContentValues;

import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.*;

public class DBCreateCommand {
    private static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TEXT_OPTION = "TEXT NOT NULL";
    private static final String PRICE_OPTION = "REAL DEFAULT 0";
    private static final String OTHER_ID_OPTION = "REAL DEFAULT 0";

    private DBCreateCommand() {
    }

    public static String createPurchaseCategory() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(PurchaseCategory.TABLE_NAME);
        builder.append("(");
        builder.append(PurchaseCategory.COLUMN_ID);
        builder.append(ID_OPTIONS + ",");
        builder.append(PurchaseCategory.COLUMN_NAME);
        builder.append(TEXT_OPTION + ",");
        builder.append(PurchaseCategory.COLUMN_PURCHASE_GROUP_ID);
        builder.append(OTHER_ID_OPTION);
        builder.append(")");
        return builder.toString();
    }

    public static String createPurchaseGroup() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(PurchaseGroup.TABLE_NAME);
        builder.append("(");
        builder.append(PurchaseGroup.COLUMN_ID);
        builder.append(ID_OPTIONS + ",");
        builder.append(PurchaseGroup.COLUMN_NAME);
        builder.append(TEXT_OPTION + ",");
        builder.append(PurchaseGroup.COLUMN_TAG);
        builder.append(TEXT_OPTION + ",");
        builder.append(PurchaseGroup.COLUMN_TILES_ID);
        builder.append(OTHER_ID_OPTION);
        builder.append(")");
        return builder.toString();
    }

    public static String createGoupTiles() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(Tiles.TABLE_NAME);
        builder.append("(");
        builder.append(Tiles.COLUMN_ID);
        builder.append(ID_OPTIONS + ",");
        builder.append(Tiles.COLUMN_PATH);
        builder.append(TEXT_OPTION);
        builder.append(")");
        return builder.toString();
    }

    public static String createPurchases() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(Purchase.TABLE_NAME);
        builder.append("(");
        builder.append(Purchase.COLUMN_ID);
        builder.append(ID_OPTIONS + ",");
        builder.append(Purchase.COLUMN_CATEGORY_ID);
        builder.append(OTHER_ID_OPTION + ",");
        builder.append(Purchase.COLUMN_DATE);
        builder.append(TEXT_OPTION + ",");
        builder.append(Purchase.COLUMN_PRICE);
        builder.append(PRICE_OPTION);
        builder.append(")");
        return builder.toString();
    }

    public static String setDefaultPurchesGroupData() {
    }

    public static String setDefaultPurchaseCategoryData() {
    }

    public static String setDefaultTilesData() {
    }

//    public void createPurchaseGroups() {
//        ContentValues tagValues = new ContentValues();
//        tagValues.put(Tiles.COLUMN_PATH, "ic_settings_black_24dp");
//        database.insert(Tiles.TABLE_NAME, null, tagValues);
//
//        ContentValues values1 = new ContentValues();
//        values1.put(PurchaseGroup.COLUMN_NAME, "DOM");
//        values1.put(PurchaseGroup.COLUMN_TAG, "house");
//        values1.put(PurchaseGroup.COLUMN_TILES_ID, "0");
//        database.insert(PurchaseGroup.TABLE_NAME, null, values1);
//
//        ContentValues values2 = new ContentValues();
//        values2.put(PurchaseGroup.COLUMN_NAME, "ROZRYWKA");
//        values2.put(PurchaseGroup.COLUMN_TAG, "entertainment");
//        values2.put(PurchaseGroup.COLUMN_TILES_ID, "0");
//        database.insert(PurchaseGroup.TABLE_NAME, null, values2);
//    }
}

