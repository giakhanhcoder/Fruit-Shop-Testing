package com.fpt.controller;


import com.fpt.model.Fruit;

import java.util.ArrayList;
import java.util.List;

public class BO_FruitShop {

    private List<Fruit> listFruit = new ArrayList<>();

    public boolean checkDuplicateFruitID(String idFruit) {
        for (Fruit f : listFruit) {
            if (f.getIdFruit().equalsIgnoreCase(idFruit)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateOriginAndNameFruit(String nameFruit, String originFruit) {
        for (Fruit f : listFruit) {
            if (f.getNameFruit().equalsIgnoreCase(nameFruit) && f.getOrigin().equalsIgnoreCase(originFruit)) {
                return true;
            }
        }
        return false;
    }

    public void addFruit(Fruit f) {
        listFruit.add(f);
    }

    public List<Fruit> getListFruit() {
        return listFruit;
    }

    public int sizeListFruit() {
        return listFruit.size();
    }

}
