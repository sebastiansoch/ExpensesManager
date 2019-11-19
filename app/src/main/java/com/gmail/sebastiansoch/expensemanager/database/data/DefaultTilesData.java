package com.gmail.sebastiansoch.expensemanager.database.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultTilesData {
    private static List<TilesData> tilesData = new ArrayList<TilesData>() {
        {
            add(new TilesData(1, "ic_menu_food"));
            add(new TilesData(2, "ic_menu_people"));
            add(new TilesData(3, "ic_menu_house"));
            add(new TilesData(4, "ic_menu_entertainment"));
            add(new TilesData(5, "ic_menu_transportation"));
            add(new TilesData(6, "ic_menu_money"));
            add(new TilesData(7, "ic_menu_other_expenses"));
        }
    };

    public static List<TilesData> getTilesData() {
        return tilesData;
    }
}
