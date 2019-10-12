package com.gmail.sebastiansoch.expensemanager.data;

public class CategoryGroup {

    private String name;
    private boolean hide;

    public CategoryGroup(String name, boolean hide) {
        this.name = name;
        this.hide = hide;
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
}
