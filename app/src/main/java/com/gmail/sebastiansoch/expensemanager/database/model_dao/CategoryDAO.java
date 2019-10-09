package com.gmail.sebastiansoch.expensemanager.database.model_dao;

public class CategoryDAO {
    private int id;
    private String name;
    private boolean hide;
    private int groupId;

    public CategoryDAO(int id, String name, boolean hide, int groupId) {
        this.id = id;
        this.name = name;
        this.hide = hide;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isHide() {
        return hide;
    }

    public int getGroupId() {
        return groupId;
    }
}
