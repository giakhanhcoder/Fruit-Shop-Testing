package com.fpt.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fpt.controller.ManageFruitShop;
import com.fpt.controller.Validation;


/**
 *
 * @author GIA KHANH
 */
public class Menu {
    private static String ops[] = {
            "Create Fruit",
            "View orders",
            "Shopping(for buyer)",
            "Exit"
    };

    public static void display(){
        ManageFruitShop m = new ManageFruitShop();
        Validation v = new Validation();
        int choice;
        do{
            System.out.println("===========================");
            System.out.println("FRUIT SHOP SYSTEM");
            for (int i = 0; i < ops.length; i++) {
                System.out.println((i+1) + ". " + ops[i]);
            }
            System.out.println("Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program");
            choice = v.getInt("Your choose: ", 1, ops.length);
            switch(choice){
                case 1 :
                    m.creatFruit();
                    break;
                case 2 :
                    m.viewOrder();
                    break;
                case 3 :
                    m.shopping();
                    break;
                case 4 :
                    break;
            }


        }while(choice != 4);
    }
}
