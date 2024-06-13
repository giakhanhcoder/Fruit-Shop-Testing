package com.fpt.controller;

import com.fpt.model.Fruit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private Fruit apple;
    private Fruit banana;

    @BeforeEach
    void setUp() {
        apple = new Fruit("1", "Apple", 0.99, 10, "USA");
        banana = new Fruit("2", "Banana", 1.99, 20, "Ecuador");
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(apple);
        order = new Order("John Doe", fruits);
    }

    @ParameterizedTest
    @CsvSource({
            "John Doe",
            "Jane Doe"
    })
    void getNameCustomer(String name) {
        order.setNameCustomer(name);
        assertEquals(name, order.getNameCustomer());
    }

    @ParameterizedTest
    @CsvSource({
            "John Doe",
            "Jane Doe"
    })
    void setNameCustomer(String newName) {
        order.setNameCustomer(newName);
        assertEquals(newName, order.getNameCustomer());
    }

    @Test
    void getListOFBoughtFruit() {
        ArrayList<Fruit> fruits = order.getListOFBoughtFruit();
        assertEquals(1, fruits.size());
        assertEquals("Apple", fruits.get(0).getNameFruit());
    }

    @ParameterizedTest
    @CsvSource({
            "2, Banana, 1.99, 20, Ecuador, 5, 2, 20",
            "1, Apple, 0.99, 10, USA, 5, 1, 15"
    })
    void addListOfBought(String id, String name, double price, int initialQty, String origin, int addQty, int expectedSize, int expectedQty) {
        Fruit fruit = new Fruit(id, name, price, initialQty, origin);
        order.addListOfBought(fruit, addQty);
        assertEquals(expectedSize, order.sizeListOFBoughtFruit());
        assertEquals(expectedQty, order.getListOFBoughtFruit().stream().filter(f -> f.getIdFruit().equals(id)).findFirst().get().getQuantity());
    }


}
