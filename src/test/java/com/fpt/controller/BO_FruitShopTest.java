package com.fpt.controller;

import com.fpt.model.Fruit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BO_FruitShopTest {

    private BO_FruitShop boFruitShop;

    @BeforeEach
    void setUp() {
        boFruitShop = new BO_FruitShop();
    }

    @ParameterizedTest
    @CsvSource({
            "1, true",    // Existing ID
            "2, false",   // Non-existing ID
            "3, false",   // Non-existing ID
            "1, true"     // Existing ID (duplicate to test idempotency)
    })
    void checkDuplicateFruitID(String id, boolean expectedResult) {
        // Tạo một loại trái cây mới với idFruit là "1"
        Fruit fruit = new Fruit("1", "Apple", 1.5, 10, "USA");

        // Thêm loại trái cây vào BO_FruitShop
        boFruitShop.addFruit(fruit);

        // Kiểm tra kết quả
        assertEquals(expectedResult, boFruitShop.checkDuplicateFruitID(id));
    }

    @ParameterizedTest
    @CsvSource({
            "Apple, USA, true",       // Existing name and origin
            "Banana, Vietnam, false", // Non-existing name and origin
            "Apple, Canada, false",   // Non-existing origin
            "Banana, USA, false"      // Non-existing name
    })
    void checkDuplicateOriginAndNameFruit(String name, String origin, boolean expectedResult) {
        // Tạo một loại trái cây mới với nameFruit là "Apple" và origin là "USA"
        Fruit fruit = new Fruit("1", "Apple", 1.5, 10, "USA");

        // Thêm loại trái cây vào BO_FruitShop
        boFruitShop.addFruit(fruit);

        // Kiểm tra kết quả
        assertEquals(expectedResult, boFruitShop.checkDuplicateOriginAndNameFruit(name, origin));
    }

    @ParameterizedTest
    @CsvSource({
            "1, Apple, 1.5, 10, USA",
            "2, Banana, 2.0, 20, Vietnam",
            "3, Orange, 3.0, 30, Brazil"
    })
    void addFruit(String id, String name, double price, int quantity, String origin) {
        // Tạo một loại trái cây mới
        Fruit fruit = new Fruit(id, name, price, quantity, origin);

        // Thêm loại trái cây vào BO_FruitShop
        boFruitShop.addFruit(fruit);

        // Kiểm tra xem danh sách trái cây trong BO_FruitShop có chứa loại trái cây mới không
        List<Fruit> fruits = boFruitShop.getListFruit();
        assertTrue(fruits.contains(fruit));
    }

    @ParameterizedTest
    @CsvSource({
            "1, Apple, 1.5, 10, USA",
            "2, Banana, 2.0, 20, Vietnam",
            "3, Orange, 3.0, 30, Brazil"
    })
    void getListFruit(String id, String name, double price, int quantity, String origin) {
        // Kiểm tra xem phương thức getListFruit có trả về đúng danh sách trái cây trong BO_FruitShop không
        assertNotNull(boFruitShop.getListFruit());
        assertEquals(0, boFruitShop.getListFruit().size());

        // Tạo một loại trái cây mới và thêm vào BO_FruitShop
        Fruit fruit = new Fruit(id, name, price, quantity, origin);
        boFruitShop.addFruit(fruit);

        // Kiểm tra lại sau khi thêm
        assertEquals(1, boFruitShop.getListFruit().size());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Apple, 1.5, 10, USA",
            "2, Banana, 2.0, 20, Vietnam",
            "3, Orange, 3.0, 30, Brazil"
    })
    void sizeListFruit(String id, String name, double price, int quantity, String origin) {
        // Kiểm tra xem phương thức sizeListFruit có trả về đúng số lượng trái cây trong BO_FruitShop không
        assertEquals(0, boFruitShop.sizeListFruit());

        // Thêm một loại trái cây mới và kiểm tra lại
        Fruit fruit = new Fruit(id, name, price, quantity, origin);
        boFruitShop.addFruit(fruit);
        assertEquals(1, boFruitShop.sizeListFruit());
    }
}
