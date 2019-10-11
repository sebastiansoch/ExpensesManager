package com.gmail.sebastiansoch.expensemanager.data;

public class Category {

    private String name;
    private boolean hide;
    private String categoryGroup;

    public Category(String name, boolean hide, String categoryGroup) {
        this.name = name;
        this.hide = hide;
        this.categoryGroup = categoryGroup;
    }

    public String getName() {
        return name;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String getCategoryGroup() {
        return categoryGroup;
    }
}
