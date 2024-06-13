package com.fpt.controller;

import java.io.InputStream;
import java.util.Scanner;

public class Validation {

    private Scanner sc;

    public Validation() {
        this.sc = new Scanner(System.in);
    }

    public void setScanner(InputStream inputStream) {
        this.sc = new Scanner(inputStream);
    }

    public String getString(String msg, String valid) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine();
            if (s.matches(valid)) {
                return s;
            }
            System.err.println("INVALID STRING! PLEASE INPUT AGAIN!");
        }
    }

    public int getInt(String msg, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                int n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    System.err.println("Please enter an integer in range " + min + " ==> " + max);
                    continue;
                }
                return n;
            } catch (NumberFormatException e) {
                System.err.println("Please enter an integer in range " + min + " ==> " + max);
            }
        }
    }

    public double getDouble(String msg, double min, double max) {
        while (true) {
            try {
                System.out.print(msg);
                double n = Double.parseDouble(sc.nextLine());
                if (n < min || n > max) {
                    System.err.println("Please enter a real number in range " + min + " ==> " + max);
                    continue;
                }
                return n;
            } catch (NumberFormatException e) {
                System.err.println("Please enter a real number in range " + min + " ==> " + max);
            }
        }

    }

    public boolean getYN(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine();
            if (s.equalsIgnoreCase("y")) {
                return true;
            } else if (s.equalsIgnoreCase("n")) {
                return false;
            }
            System.err.println("Please input only Y/N");
        }
    }

}
