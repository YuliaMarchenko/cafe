package com.telran;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileProductStore fileProductStore = new FileProductStore();

        Product product = new Product("tea", 2567, "desc1", "drinks", true);
        fileProductStore.add(product);

        Product product2 = new Product("coffee", 34532, "desc3", "drinks", false);
        fileProductStore.add(product2);

        for (String each : fileProductStore.list()) {
            System.out.println(each);
        }

        for (String each : fileProductStore.listAvailable()) {
            System.out.println(each);
        }

        System.out.println(fileProductStore.getProductInfoByPath("cafe/products/drinks/coffee.cafe"));
        System.out.println(fileProductStore.getProductInfoByLookup("drinks", "tea"));

        fileProductStore.deleteByLookup("drinks", "tea");
    }
}
