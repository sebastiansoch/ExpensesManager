package com.gmail.sebastiansoch.expensemanager.database.model_dao;

public class TilesDAO {
    private int id;
    private String path;

    public TilesDAO(int id, String path) {
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
