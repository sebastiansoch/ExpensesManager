package com.gmail.sebastiansoch.expensemanager.utils;

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

    @NonNull
    @Override
    public String toString() {
        return builder.toString();
    }
}
