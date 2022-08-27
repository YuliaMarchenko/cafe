package com.telran;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        String productFromConsole = getConsoleMessage("Enter product. Format: name^price^description^category^isAvailable). Price in cents. IsAvalible: true or false.");
//        Product product = Product.fromString(productFromConsole);
       FileProductStore fileProductStore = new FileProductStore();
//        fileProductStore.add(product);

//        for(String each:fileProductStore.list()){
//            System.out.println(each);
//        }

//        for(String each:fileProductStore.listAvailable()){
//            System.out.println(each);
//        }
        System.out.println(fileProductStore.getProductInfoByPath("cafe/products/drinks/coffe.cafe"));
//        System.out.println(fileProductStore.getProductInfoByLookup("drinks", "tea"));

     //   fileProductStore.deleteByLookup("drinks", "tea");

//        Product product = Product.fromString("tea^2356^desc1^drinks^true");
//        fileProductStore.add(product);
//
//        Product product2 = Product.fromString("coffe^2343356^desc2^drinks^false");
//        fileProductStore.add(product2);
    }

    private static String getConsoleMessage(String caption) {
        System.out.println(caption);
        return new Scanner(System.in).nextLine();
    }
}
