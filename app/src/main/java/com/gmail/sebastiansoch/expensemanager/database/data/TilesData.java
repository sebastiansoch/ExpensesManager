package com.gmail.sebastiansoch.expensemanager.database.data;

class TilesData {
    private int id;
    private String iconPath;

    public TilesData(int id, String iconPath) {
        this.id = id;
        this.iconPath = iconPath;
    }

    public int getId() {
        return id;
    }

    public String getIconPath() {
        return iconPath;
    }
}
