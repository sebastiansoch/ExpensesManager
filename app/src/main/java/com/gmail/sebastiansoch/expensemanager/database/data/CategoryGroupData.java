package com.gmail.sebastiansoch.expensemanager.database.data;

public class CategoryGroupData {
    private int id;
    private final String name;
    private final String tag;
    private final int tileId;

    public CategoryGroupData(int id, String name, String tag, int tileId) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.tileId = tileId;
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

    public int getTileId() {
        return tileId;
    }
}
