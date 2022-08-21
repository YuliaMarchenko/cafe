package com.telran;

import java.util.List;

public interface ProductStore {
    public boolean add(Product product);

    public List<String> list();

    public List<String> listAvailable();
}
