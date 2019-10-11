package com.gmail.sebastiansoch.expensemanager.data;

public class CategoryGroupTilesInfo {
    private String purchaseGroup;
    private String tileTag;
    private String tilePath;

    public CategoryGroupTilesInfo(String purchaseGroup, String tileTag, String tilePath) {
        this.purchaseGroup = purchaseGroup;
        this.tileTag = tileTag;
        this.tilePath = tilePath;
    }

    public String getPurchaseGroup() {
        return purchaseGroup;
    }

    public void setPurchaseGroup(String purchaseGroup) {
        this.purchaseGroup = purchaseGroup;
    }

    public String getTileTag() {
        return tileTag;
    }

    public void setTileTag(String tileTag) {
        this.tileTag = tileTag;
    }

    public String getTilePath() {
        return tilePath;
    }

    public void setTilePath(String tilePath) {
        this.tilePath = tilePath;
    }
}
