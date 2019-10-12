package com.gmail.sebastiansoch.expensemanager.database.model;

public class TilesDTO {
    private int id;
    private String path;

    public TilesDTO(int id, String path) {
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
