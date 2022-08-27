package com.telran;

import java.io.File;

public class ProductFileHelper {
    public static final String PRODUCTS_PATH = "cafe/products.cafe";
    public static final String PATH_PREFIX = "cafe/products/";
    public static final String FILE_EXTENSION = ".cafe";

    public static String getProductsPath(Product product){
        return PATH_PREFIX + product.getCategory();
    }

    public static String getPathName(Product product){
        return getProductsPath(product) + File.separator + product.getName() + FILE_EXTENSION;
    }
}
