package com.gmail.sebastiansoch.expensemanager.database;

import android.provider.BaseColumns;

public class PurchaseDBSchema {
    private PurchaseDBSchema() {
    }

    public static class PurchaseCategory implements BaseColumns {
        public static final String TABLE_NAME = "purchase_category";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PURCHASE_GROUP_ID = "group_id";
    }

    public static class PurchaseGroup implements BaseColumns {
        public static final String TABLE_NAME = "purchase_group";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TAG = "tag";
        public static final String COLUMN_TILES_ID = "tiles_id";
    }

    public static class Tiles implements BaseColumns {
        public static final String TABLE_NAME = "purchase_group_tiles";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PATH = "path";
    }

    public static class Purchase implements BaseColumns {
        public static final String TABLE_NAME = "purchase";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_PRICE = "price";
    }

 }
