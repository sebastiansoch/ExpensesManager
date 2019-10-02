package com.gmail.sebastiansoch.expensemanager.database.schema;

import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

public final class DBDeleteCommand {

    private DBDeleteCommand() {
    }

    public static String deletePurchase() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(DBSchema.Purchase.TABLE_NAME);
        return builder.toString();
    }

    public static String deleteTiles() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(DBSchema.Tiles.TABLE_NAME);
        return builder.toString();
    }

    public static String deletePurchaseGroup() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(DBSchema.PurchaseGroup.TABLE_NAME);
        return builder.toString();
    }

    public static String deletePurchaseCategory() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(DBSchema.PurchaseCategory.TABLE_NAME);
        return builder.toString();
    }

}
