package com.gmail.sebastiansoch.expensemanager.database.model_dao;

public class CategoryGroupDAO {
    private int id;
    private String name;
    private String tag;
    private boolean hide;
    private int tilesId;

    public CategoryGroupDAO(int id, String name, String tag, boolean hide, int tilesId) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.hide = hide;
        this.tilesId = tilesId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public boolean isHide() {
        return hide;
    }

    public int getTilesId() {
        return tilesId;
    }
}
