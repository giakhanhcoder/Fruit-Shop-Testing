package com.fpt.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    private Fruit fruit;

    @BeforeEach
    void setUp() {
        fruit = new Fruit("1", "Apple", 0.99, 100, "USA");
    }

    @Test
    void testNullValues() {
        Fruit emptyFruit = new Fruit();
        assertNull(emptyFruit.getIdFruit());
        assertNull(emptyFruit.getNameFruit());
        assertEquals(0.0, emptyFruit.getPrice());
        assertEquals(0, emptyFruit.getQuantity());
        assertNull(emptyFruit.getOrigin());
    }

    @Test
    void testBooleanConditions() {
        assertTrue(fruit.getPrice() > 0);
        assertFalse(fruit.getQuantity() < 0);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.99, 1.49, 2.99})
    void testSetPriceParameterized(double price) {
        Fruit fruit = new Fruit();
        fruit.setPrice(price);
        assertEquals(price, fruit.getPrice());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 50, 100, 200})
    void testSetQuantityParameterized(int quantity) {
        Fruit fruit = new Fruit();
        fruit.setQuantity(quantity);
        assertEquals(quantity, fruit.getQuantity());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Apple, 0.99, 100, USA",
            "2, Banana, 1.29, 50, Ecuador",
            "3, Orange, 0.79, 75, Spain"
    })
    void testFruitConstructorParameterized(String id, String name, double price, int quantity, String origin) {
        Fruit fruit = new Fruit(id, name, price, quantity, origin);
        assertEquals(id, fruit.getIdFruit());
        assertEquals(name, fruit.getNameFruit());
        assertEquals(price, fruit.getPrice());
        assertEquals(quantity, fruit.getQuantity());
        assertEquals(origin, fruit.getOrigin());
    }

    // @ParameterizedTest testSetOriginParameterizedTest
    @ParameterizedTest
    @ValueSource(strings = {"USA", "Vietnam", "China"})
    void testSetOriginParameterized(String origin) {
        Fruit fruit = new Fruit();
        fruit.setOrigin(origin);
        assertEquals(origin, fruit.getOrigin());
    }

    // @ParameterizedTest testSetNameFruit
    @ParameterizedTest
    @ValueSource(strings = {"Apple", "Banana", "Orange"})
    void testSetNameFruit(String name) {
        Fruit fruit = new Fruit();
        fruit.setNameFruit(name);
        assertEquals(name, fruit.getNameFruit());
    }

    // @ParameterizedTest testSetIdFruit
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void testSetIdFruit(String id) {
        Fruit fruit = new Fruit();
        fruit.setIdFruit(id);
        assertEquals(id, fruit.getIdFruit());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Apple",
            "2, Banana",
            "3, Orange"
    })
    void testGetIdFruitParameterized(String id, String name) {
        Fruit fruit = new Fruit(id, name, 0.0, 0, "");
        assertEquals(id, fruit.getIdFruit());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Apple",
            "2, Banana",
            "3, Orange"
    })
    void testGetNameFruitParameterized(String id, String name) {
        Fruit fruit = new Fruit(id, name, 0.0, 0, "");
        assertEquals(name, fruit.getNameFruit());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0.99",
            "2, 1.49",
            "3, 2.99"
    })
    void testGetPriceParameterized(String id, double price) {
        Fruit fruit = new Fruit(id, "", price, 0, "");
        assertEquals(price, fruit.getPrice());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 100",
            "2, 50",
            "3, 75"
    })
    void testGetQuantityParameterized(String id, int quantity) {
        Fruit fruit = new Fruit(id, "", 0.0, quantity, "");
        assertEquals(quantity, fruit.getQuantity());
    }

    @ParameterizedTest
    @CsvSource({
            "1, USA",
            "2, Vietnam",
            "3, China"
    })
    void testGetOriginParameterized(String id, String origin) {
        Fruit fruit = new Fruit(id, "", 0.0, 0, origin);
        assertEquals(origin, fruit.getOrigin());
    }
}
