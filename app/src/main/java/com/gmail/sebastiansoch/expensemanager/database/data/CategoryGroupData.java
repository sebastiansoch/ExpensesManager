package com.gmail.sebastiansoch.expensemanager.database.data;

class CategoryGroupData {
    private int id;
    private final String name;
    private final String tag;
    private final int tile_id;

    public CategoryGroupData(int id, String name, String tag, int tile_id) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.tile_id = tile_id;
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

    public int getTile_id() {
        return tile_id;
    }
}
