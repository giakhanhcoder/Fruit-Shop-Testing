package com.fpt.controller;

import com.fpt.model.Fruit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class FruitConsoleTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("1\nApple\n0.99\n100\nUSA\n".getBytes());

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @Test
    void testConsoleInputOutput() {
        // Simulate reading from console and creating a Fruit object
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String id = scanner.nextLine();
        String name = scanner.nextLine();
        double price = scanner.nextDouble();
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        String origin = scanner.nextLine();

        Fruit fruit = new Fruit(id, name, price, quantity, origin);

        // Simulate writing to console
        System.out.println(fruit.toString());

        // Check the output
        String expectedOutput = "Fruit{idFruit=1, nameFruit=Apple, price=0.99, quantity=100, origin=USA}";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void testInvalidInput() {
        // Simulate invalid input (e.g., non-numeric price and quantity)
        ByteArrayInputStream invalidInContent = new ByteArrayInputStream("1\nApple\ninvalidPrice\ninvalidQuantity\nUSA\n".getBytes());
        System.setIn(invalidInContent);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String id = scanner.nextLine();
        String name = scanner.nextLine();

        // Expect InputMismatchException when trying to read invalid price and quantity
        assertThrows(InputMismatchException.class, () -> {
            double price = scanner.nextDouble();
        });

        assertThrows(InputMismatchException.class, () -> {
            int quantity = scanner.nextInt();
        });
    }

    @Test
    void testConsoleOutputWithDifferentFruit() {
        // Create a different Fruit object and simulate writing to console
        Fruit differentFruit = new Fruit("3", "Watermelon", 27.04, 912, "VietNam");
        System.out.println(differentFruit.toString());

        // Check the output
        String expectedOutput = "Fruit{idFruit=1, nameFruit=Apple, price=0.99, quantity=100, origin=USA}";
        assertFalse(outContent.toString().contains(expectedOutput));
    }
}
