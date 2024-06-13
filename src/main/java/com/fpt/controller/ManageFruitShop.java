package com.fpt.controller;


import com.fpt.model.Fruit;

import java.util.ArrayList;
import java.util.Enumeration;

public class ManageFruitShop {

    BO_FruitShop bo = new BO_FruitShop();
    BO_Order boOrder = new BO_Order();

    Validation v = new Validation();
    public static final String STRING_VALID = "[a-zA-Z0-9]+";
    public static final String NAME_VALID = "^[a-zA-Z ]+[a-zA-Z]*$";

    public ManageFruitShop() {

    }

    public void creatFruit() {
        while (true) {
            String idFruit;

            //check duplicate id
            while (true) {
                idFruit = v.getString("Input Id Fruit: ", STRING_VALID);
                if (!bo.checkDuplicateFruitID(idFruit)) {
                    break;
                }
                System.err.println("Duplicate ID Fruit");
            }

            String nameFruit = v.getString("Input Fruit Name: ", NAME_VALID);
            double price = v.getDouble("Input price: ", 0.001, 10000);
            int quantity = v.getInt("Input quantity: ", 1, 10000);
            String origin = v.getString("Input origin: ", NAME_VALID);

            //if duplicate name and origin -> creat another fruit or not
            if (bo.checkDuplicateOriginAndNameFruit(nameFruit, origin)) {
                if (v.getYN(("Fruit is already created! Do you want creat another fruit?: "))) {
                    while (true) {
                        System.out.println("Please creat another fruit!");
                        nameFruit = v.getString("Input Fruit Name: ", NAME_VALID);
                        origin = v.getString("Input origin: ", NAME_VALID);
                        if (!bo.checkDuplicateOriginAndNameFruit(nameFruit, origin)) {
                            break;
                        }
                    }
                } else {
                    //idFruit, String nameFruit, double price, int quantity, String origin
                    System.out.printf("%-5s%-8s%-10s%-12s%-8s\n", "Id", "Fruit", "Price", "Quantity", "Origin");
                    for (Fruit fruit : bo.getListFruit()) {
                        System.out.printf("%-5s%-8s%-10s%-12s%-8s\n", fruit.getIdFruit(), fruit.getNameFruit(), fruit.getPrice(), fruit.getQuantity(), fruit.getOrigin());
                    }
                    return;
                }
            }

            //add to Fruit List
            Fruit addFruit = new Fruit(idFruit, nameFruit, price, quantity, origin);
            bo.addFruit(addFruit);
            if (v.getYN("Do you want to continue(Y/N)?: ")) {

            } else {
                System.out.printf("%-5s%-8s%-10s%-12s%-8s\n", "Id", "Fruit", "Price", "Quantity", "Origin");
                for (Fruit fruit : bo.getListFruit()) {
                    System.out.printf("%-5s%-8s%-10s%-12s%-8s\n", fruit.getIdFruit(), fruit.getNameFruit(), fruit.getPrice(), fruit.getQuantity(), fruit.getOrigin());
                }
                break;
            }
        }
    }

    public void shopping() {
        Order order = new Order();
        if (bo.getListFruit().isEmpty()) {
            System.out.println("\nNOTHING TO DISPLAY!");
            return;
        }
        double total = 0;
        while (true) {
            int i = 1;
            System.out.println("List of Fruit:");
            System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
            for (Fruit fruit : bo.getListFruit()) {
                System.out.printf("%-7s%-13d%-17s%-17s%-5s\n", "", i++, fruit.getNameFruit(), fruit.getOrigin(), fruit.getPrice());
            }
            //select options
            int choose = v.getInt("Selects items: ", 1, bo.sizeListFruit());
            Fruit fruitBought = bo.getListFruit().get(choose - 1);
            System.out.println("You selected: " + fruitBought.getNameFruit());
            int quantity = v.getInt("Please input quanity: ", 1, fruitBought.getQuantity());
            Fruit addFruit = new Fruit(fruitBought.getIdFruit(), fruitBought.getNameFruit(), fruitBought.getPrice(), quantity, fruitBought.getOrigin());
            order.addListOfBought(addFruit, quantity);
            //update quantity after customer order
            fruitBought.setQuantity(fruitBought.getQuantity() - quantity);
            if (fruitBought.getQuantity() == 0) {
                bo.getListFruit().remove(fruitBought);
            }
            if (v.getYN("Do you want oder now (Y/N)?: ") || bo.getListFruit().isEmpty()) {
                if (bo.getListFruit().isEmpty()) {
                    System.out.println("List Fruit is empty now!");
                }
                int j = 1;
                System.out.println("Product | Quantity | Price | Amount");
                for (Fruit fruit : order.getListOFBoughtFruit()) {
                    System.out.printf("%-1d.%-12s%-11s%-8s%-8s\n", j++, fruit.getNameFruit(), fruit.getQuantity(), fruit.getPrice(), (fruit.getQuantity() * fruit.getPrice()));
                    total += fruit.getPrice() * fruit.getQuantity();
                }
                System.out.println("Total: " + total + "$");
                String nameCustomers = v.getString("Input your name: ", NAME_VALID);

                boOrder.addOrder(new Order(nameCustomers, order.getListOFBoughtFruit()));
                break;
            }
        }
    }

    public void viewOrder() {
        if (boOrder.getOrder().isEmpty()) {
            System.out.println("\nNOTHING TO DISPLAY!");
            return;
        }

        Enumeration<Integer> keys = boOrder.getOrder().keys();
        while (keys.hasMoreElements()) {
            Integer number = keys.nextElement();
            int j = 1;
            double total = 0;
            String name = boOrder.getOrder().get(number).getNameCustomer();
            ArrayList<Fruit> fruits = boOrder.getOrder().get(number).getListOFBoughtFruit();
            System.out.println("Customer: " + name);
            System.out.println("Product | Quantity | Price | Amount");
            for (Fruit f : fruits) {
                System.out.printf("%-1d.%-12s%-11s%-8s%7.2f\n", j++, f.getNameFruit(), f.getQuantity(), f.getPrice(), (f.getQuantity() * f.getPrice()));
                total += f.getQuantity() * f.getPrice();
            }
            System.out.println("Total: " + total);
        }
    }
}
