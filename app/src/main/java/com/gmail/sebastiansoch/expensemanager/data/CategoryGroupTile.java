package com.gmail.sebastiansoch.expensemanager.data;

import java.io.Serializable;

public class CategoryGroupTile implements Serializable {

    private static final long serialVersionUID = 1L;

    private CategoryGroup categoryGroup;
    private String tilesTag;
    private String tilesIconPath;

    public CategoryGroupTile() {
    }

    public CategoryGroupTile(CategoryGroup categoryGroup, String tilesTag, String tilesIconPath) {
        this.categoryGroup = categoryGroup;
        this.tilesTag = tilesTag;
        this.tilesIconPath = tilesIconPath;
    }

    public CategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(CategoryGroup categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

    public String getTilesTag() {
        return tilesTag;
    }

    public void setTilesTag(String tilesTag) {
        this.tilesTag = tilesTag;
    }

    public String getTilesIconPath() {
        return tilesIconPath;
    }

    public void setTilesIconPath(String tilesIconPath) {
        this.tilesIconPath = tilesIconPath;
    }
}
