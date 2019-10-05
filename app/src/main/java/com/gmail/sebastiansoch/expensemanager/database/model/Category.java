package com.gmail.sebastiansoch.expensemanager.database.model;

public class Category {
    private String name;
    private String tag;
    private boolean hide;

    public Category(String name, String tag, boolean hide) {
        this.name = name;
        this.tag = tag;
        this.hide = hide;
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
}
