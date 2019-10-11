package com.gmail.sebastiansoch.expensemanager.data;

import java.io.Serializable;

public class CategoryGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private boolean hide;

    public CategoryGroup() {
    }

    public CategoryGroup(String name, boolean hide) {
        this.name = name;
        this.hide = hide;
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
}
