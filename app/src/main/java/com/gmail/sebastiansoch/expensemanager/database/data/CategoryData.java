package com.gmail.sebastiansoch.expensemanager.database.data;

public class CategoryData {

    private String name;
    private int groupId;

    public CategoryData(String name, int groupId) {
        this.name = name;
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public int getGroupId() {
        return groupId;
    }
}
