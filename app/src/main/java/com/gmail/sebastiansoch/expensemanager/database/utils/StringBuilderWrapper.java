package com.gmail.sebastiansoch.expensemanager.database.utils;

import androidx.annotation.NonNull;

public class StringBuilderWrapper {

    StringBuilder builder;

    public StringBuilderWrapper(String string) {
        builder =  new StringBuilder(string);
    }

    public StringBuilderWrapper() {
        builder =  new StringBuilder();
    }

    public StringBuilderWrapper append(String string) {
        builder.append(" ");
        builder.append(string);
        return this;
    }

    public StringBuilderWrapper appendColumn(String tableName, String columnName) {
        builder.append(" ").append(tableName);
        builder.append(".").append(columnName);
        return this;
    }

    public StringBuilderWrapper appendValue(String value) {
        builder.append(" \"");
        builder.append(value);
        builder.append("\"");
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return builder.toString();
    }

}
