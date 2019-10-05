package com.gmail.sebastiansoch.expensemanager.database.model;

public class Tiles {
    private int id;
    private String path;

    public Tiles(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
