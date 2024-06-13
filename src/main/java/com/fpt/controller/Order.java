
package com.fpt.controller;


import com.fpt.model.Fruit;

import java.util.ArrayList;

public class Order {

    private String nameCustomer;
    private ArrayList<Fruit> listofbought = new ArrayList<>();

    public Order() {
    }

    public Order(String nameCustomer, ArrayList<Fruit> listofbought) {
        this.nameCustomer = nameCustomer;
        this.listofbought = listofbought;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void isEmpty() {
        listofbought = new ArrayList<>();
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public ArrayList<Fruit> getListOFBoughtFruit() {
        ArrayList<Fruit> copy = new ArrayList<>();
        for (int i = 0; i < listofbought.size(); i++) {
            copy.add(listofbought.get(i));
        }
        return copy;
    }

    public int sizeListOFBoughtFruit() {
        return listofbought.size();
    }

    public void addListOfBought(Fruit f, int quantity) {
        if (checkDuplicateFruit(f)) {
            int pos = posDuplicateFruit(f);
            int oldQuantity = listofbought.get(pos).getQuantity();
            listofbought.get(pos).setQuantity(oldQuantity + quantity);
        } else {
            listofbought.add(f);
        }
    }

    public boolean checkDuplicateFruit(Fruit fruit) {
        for (Fruit f : listofbought) {
            if (f.getIdFruit().equalsIgnoreCase(fruit.getIdFruit())) {
                return true;
            }
        }
        return false;
    }

    public int posDuplicateFruit(Fruit fruit) {
        int count = 0;
        for (Fruit f : listofbought) {
            if (f.getIdFruit().equalsIgnoreCase(fruit.getIdFruit())) {
                break;
            }
            count++;
        }
        return count;
    }
}
