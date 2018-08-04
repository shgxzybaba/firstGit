package com.shgxzyjune.personalproject.Utilities;

public enum Faculty {
    SCIENCE("Science"),
    ARTS("Arts"),
    COMMERCIAL("Commercial");

    private final String value;


    Faculty(String value) {
        this.value = value;

    }

    public String getValue() {
        return this.value;
    }
}
