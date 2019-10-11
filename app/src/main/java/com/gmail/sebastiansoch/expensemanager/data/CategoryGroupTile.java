package com.gmail.sebastiansoch.expensemanager.data;

public class CategoryGroupTile {

    private CategoryGroup categoryGroup;
    private String tilesTag;
    private String tilesIconPath;

    public CategoryGroupTile(CategoryGroup categoryGroup, String tilesTag, String tilesIconPath) {
        this.categoryGroup = categoryGroup;
        this.tilesTag = tilesTag;
        this.tilesIconPath = tilesIconPath;
    }

    public CategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    public String getTilesTag() {
        return tilesTag;
    }

    public String getTilesIconName() {
        return tilesIconPath;
    }


}
