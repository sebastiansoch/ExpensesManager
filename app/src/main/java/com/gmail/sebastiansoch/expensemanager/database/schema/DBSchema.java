package com.gmail.sebastiansoch.expensemanager.database.schema;

import android.provider.BaseColumns;

public class DBSchema {
    private DBSchema() {
    }

    public static class SchemaCategory implements BaseColumns {
        public static final String TABLE_NAME = "purchase_category";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IS_HIDE = "is_hide";
        public static final String COLUMN_CATEGORY_GROUP_ID = "group_id";
    }

    public static class SchemaCategoryGroup implements BaseColumns {
        public static final String TABLE_NAME = "purchase_group";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TAG = "tag";
        public static final String COLUMN_IS_HIDE = "is_hide";
        public static final String COLUMN_TILES_ID = "tiles_id";
    }

    public static class SchemaTiles implements BaseColumns {
        public static final String TABLE_NAME = "purchase_group_tiles";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PATH = "path";
    }

    public static class SchemaPurchase implements BaseColumns {
        public static final String TABLE_NAME = "purchase";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CATEGORY_GROUP_ID = "category_group_id";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_PURCHASE_DATE = "purchase_date";
        public static final String COLUMN_ENTRY_DATE = "entry_date";
        public static final String COLUMN_PRICE = "price";
    }

 }
