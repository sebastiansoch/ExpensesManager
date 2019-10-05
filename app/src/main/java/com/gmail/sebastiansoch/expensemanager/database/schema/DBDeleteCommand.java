package com.gmail.sebastiansoch.expensemanager.database.schema;

import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.*;

public final class DBDeleteCommand {

    private DBDeleteCommand() {
    }

    public static String deletePurchase() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(SchemaPurchase.TABLE_NAME);
        return builder.toString();
    }

    public static String deleteTiles() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(SchemaTiles.TABLE_NAME);
        return builder.toString();
    }

    public static String deletePurchaseGroup() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(SchemaCategoryGroup.TABLE_NAME);
        return builder.toString();
    }

    public static String deletePurchaseCategory() {
        StringBuilderWrapper builder = new StringBuilderWrapper("DROP TABLE IF EXISTS");
        builder.append(SchemaCategory.TABLE_NAME);
        return builder.toString();
    }

}
