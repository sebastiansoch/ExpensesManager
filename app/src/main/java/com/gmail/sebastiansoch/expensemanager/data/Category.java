package com.gmail.sebastiansoch.expensemanager.data;

import java.io.Serializable;

public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private boolean hide;
    private String categoryGroup;

    public Category() {
    }

    public Category(String name, boolean hide, String categoryGroup) {
        this.name = name;
        this.hide = hide;
        this.categoryGroup = categoryGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCategoryGroup(String categoryGroup) {
        this.categoryGroup = categoryGroup;
    }
}
