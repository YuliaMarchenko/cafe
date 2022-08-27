package com.telran;

import java.io.IOException;
import java.util.List;

public interface ProductStore {
    boolean add(Product product);

    List<String> list();

    List<String> listAvailable();

    Product getProductInfoByPath(String path) throws IOException, ClassNotFoundException;

    Product getProductInfoByLookup(String category, String name) throws IOException, ClassNotFoundException;

    boolean deleteByPath(String path);

    boolean deleteByLookup(String category, String name);
}
