package com.telran;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String productFromConsole = getConsoleMessage("Enter product. Format: name^price^description^category^isAvailable). Price in cents. IsAvalible: true or false.");
        Product product = Product.fromString(productFromConsole);
        FileProductStore fileProductStore = new FileProductStore();
        fileProductStore.add(product);
    }

    private static String getConsoleMessage(String caption) {
        System.out.println(caption);
        return new Scanner(System.in).nextLine();
    }
}
