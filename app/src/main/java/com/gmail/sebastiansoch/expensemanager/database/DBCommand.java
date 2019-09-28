package com.gmail.sebastiansoch.expensemanager.database;

import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

import static com.gmail.sebastiansoch.expensemanager.database.PurchaseDBSchema.Purchase;
import static com.gmail.sebastiansoch.expensemanager.database.PurchaseDBSchema.PurchaseCategory;
import static com.gmail.sebastiansoch.expensemanager.database.PurchaseDBSchema.PurchaseGroup;
import static com.gmail.sebastiansoch.expensemanager.database.PurchaseDBSchema.Tiles;

public final class DBCommand {
    private static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TEXT_OPTION = "TEXT NOT NULL";
    private static final String PRICE_OPTION = "REAL DEFAULT 0";
    private static final String OTHER_ID_OPTION = "REAL DEFAULT 0";

    private DBCommand() {
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

//    public static String deleteDBEntries() {
//        deletePurchaseCategory();
//        deletePurchaseGroup();
//        deleteTiles();
//        deletePurchase();
//    }

    public static String deletePurchase() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(Purchase.TABLE_NAME);
        return builder.toString();
    }

    public static String deleteTiles() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(Tiles.TABLE_NAME);
        return builder.toString();
    }

    public static String deletePurchaseGroup() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(PurchaseGroup.TABLE_NAME);
        return builder.toString();
    }

    public static String deletePurchaseCategory() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(PurchaseCategory.TABLE_NAME);
        return builder.toString();
    }


}
