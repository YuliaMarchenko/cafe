package com.telran;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductStore {
    public boolean add(Product product);

    public List<String> list();

    public List<String> listAvailable();

    public Product getProductInfoByPath(String path) throws FileNotFoundException;

    public Product getProductInfoByLookup(String category, String name) throws FileNotFoundException;

    public boolean deleteByPath(String path);

    public boolean deleteByLookup(String category, String name);
}
