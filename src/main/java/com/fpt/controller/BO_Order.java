/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.controller;

import java.util.Hashtable;

/**
 * @author GIA KHANH
 */
public class BO_Order {

    private int number = 0;
    private Hashtable<Integer, Order> order = new Hashtable<>();

    public void addOrder(Order o) {
        order.put(++number, o);
    }

    public Hashtable<Integer, Order> getOrder() {
        return order;
    }


}
