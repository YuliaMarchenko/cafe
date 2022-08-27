package com.telran;

import java.io.Serializable;

public class ProductListItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String path;
    private boolean isAvailable;

    public ProductListItem(String name, String path, boolean isAvailable) {
        this.name = name;
        this.path = path;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
