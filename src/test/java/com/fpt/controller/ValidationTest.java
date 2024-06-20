package com.fpt.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    private Validation validation;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        validation = new Validation();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @ParameterizedTest
    @CsvSource({"testString, testString\n"})
    void getString(String expected, String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        validation.setScanner(System.in);  // Reset the scanner to read from the new input stream
        String result = validation.getString("Enter a string: ", ".*");
        assertEquals(expected, result);
    }



    @ParameterizedTest
    @CsvSource({
            "5, 5",
            "7, 7"
    })
    void getInt(int expected, String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        validation.setScanner(System.in);  // Reset the scanner to read from the new input stream
        int result = validation.getInt("Enter an integer between 1 and 10: ", 1, 10);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "5.5, 5.5",
            "7.3, 7.3"
    })
    void getDouble(double expected, String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        validation.setScanner(System.in);  // Reset the scanner to read from the new input stream
        double result = validation.getDouble("Enter a double between 1.0 and 10.0: ", 1.0, 10.0);
        assertEquals(expected, result, 0.0001);
    }



    @ParameterizedTest
    @ValueSource(strings = {"y\n", "Y\n"})
    void getYN_Yes(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        validation.setScanner(System.in);  // Reset the scanner to read from the new input stream
        boolean result = validation.getYN("Enter Y/N: ");
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"n\n", "N\n"})
    void getYN_No(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        validation.setScanner(System.in);  // Reset the scanner to read from the new input stream
        boolean result = validation.getYN("Enter Y/N: ");
        assertFalse(result);
    }

    @ParameterizedTest
    @CsvSource({
            ", abc\n",
            ", -1\n",
            ", 11\n"
    })
    void getInt_Invalid(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        validation.setScanner(System.in);
        assertThrows(NumberFormatException.class, () -> validation.getInt("Enter an integer between 1 and 10: ", 1, 10));
    }

    @ParameterizedTest
    @CsvSource({
            ", abc\n",
            ", -1.0\n",
            ", 11.0\n"
    })
    void getDouble_Invalid(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        validation.setScanner(System.in);
        assertThrows(NumberFormatException.class, () -> validation.getDouble("Enter a double between 1.0 and 10.0: ", 1.0, 10.0));
    }

    @ParameterizedTest
    @CsvSource({
            ", \n",
            ", !@#\n"
    })
    void getString_Invalid(String expected, String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        validation.setScanner(System.in);
        String result = validation.getString("Enter a string: ", "^[a-zA-Z0-9]+$");
        assertNull(result);  // Assuming your getString method returns null for invalid input
    }
}
