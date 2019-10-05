package com.gmail.sebastiansoch.expensemanager.database.model;

public class Category {
    private int id;
    private String name;
    private String tag;
    private boolean hide;
    private int groupId;

    public Category(int id, String name, String tag, boolean hide, int groupId) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.hide = hide;
        this.groupId = groupId;
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

    public int getGroupId() {
        return groupId;
    }
}
