package com.gmail.sebastiansoch.expensemanager.database.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultTilesData {
    private static List<TilesData> tilesData = new ArrayList<TilesData>() {
        {
            add(new TilesData(1, "ic_categories_food"));
            add(new TilesData(2, "ic_categories_people"));
            add(new TilesData(3, "ic_categories_house"));
            add(new TilesData(4, "ic_categories_entertainment"));
            add(new TilesData(5, "ic_categories_transportation"));
            add(new TilesData(6, "ic_categories_money"));
            add(new TilesData(7, "ic_categories_other_expenses"));
        }
    };

    public static List<TilesData> getTilesData() {
        return tilesData;
    }
}
