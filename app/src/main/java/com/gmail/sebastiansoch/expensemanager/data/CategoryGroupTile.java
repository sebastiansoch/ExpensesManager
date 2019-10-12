package com.gmail.sebastiansoch.expensemanager.data;

public class CategoryGroupTile {

    private String categoryGroupName;
    private String tilesTag;
    private String tilesIconPath;

    public CategoryGroupTile(String categoryGroupName, String tilesTag, String tilesIconPath) {
        this.categoryGroupName = categoryGroupName;
        this.tilesTag = tilesTag;
        this.tilesIconPath = tilesIconPath;
    }

    public String getCategoryGroupName() {
        return categoryGroupName;
    }

    public String getTilesTag() {
        return tilesTag;
    }

    public String getTilesIconPath() {
        return tilesIconPath;
    }
}
