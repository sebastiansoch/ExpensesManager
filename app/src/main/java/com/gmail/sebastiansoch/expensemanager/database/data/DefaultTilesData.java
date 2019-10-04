package com.gmail.sebastiansoch.expensemanager.database.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultTilesData {
    private static List<TilesData> tilesData = new ArrayList<TilesData>() {
        {
            add(new TilesData(1, "ic_settings_black_24dp"));
            add(new TilesData(2, "ic_settings_black_24dp"));
            add(new TilesData(3, "ic_settings_black_24dp"));
            add(new TilesData(4, "ic_settings_black_24dp"));
            add(new TilesData(5, "ic_settings_black_24dp"));
            add(new TilesData(6, "ic_settings_black_24dp"));
            add(new TilesData(7, "ic_settings_black_24dp"));
            add(new TilesData(8, "ic_settings_black_24dp"));
        }
    };

    public static List<TilesData> getTilesData() {
        return tilesData;
    }
}
