package client;

import machine.VendingMachine;

import java.util.Scanner;

public class Client {
    private static int INITIAL_PRODUCT_COUNT = 6;
    private static double  PRODUCT_PRICE = 10.0;

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);

        VendingMachine vendingMachine = new VendingMachine(INITIAL_PRODUCT_COUNT, PRODUCT_PRICE);

        vendingMachine.displayDetails();

        while(true) {

            try {
                System.out.print("Put money amount here-> ");
                double amount = Double.parseDouble(scr.nextLine());
                vendingMachine.acceptMoney(amount);
            } catch (Exception e) {
                System.out.println("Enter a number!!");
            }
        }
    }
}
