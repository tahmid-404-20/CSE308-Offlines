package client;

import observer.Observer;
import observer.PremiumUser;
import observer.RegularUser;
import subject.ServerABC;
import util.ClientStatus;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        ServerABC serverABC = new ServerABC("ABC", "DEF");

        int regularUserCount = 0;
        int premiumUserCount = 0;

        while(true) {
            while(true) {
                try{
                    System.out.println();
                    System.out.println("Main: \nWould you like to -");
                    System.out.println("1. Create Premium User");
                    System.out.println("2. Create Regular User");
                    System.out.println("3. Change Server Sate");
                    System.out.println("4. Exit");
                    System.out.print("Enter (1/2/3/4) to make your choice: ");

                    int choice = Integer.parseInt(scr.nextLine());

                    if(choice == 1) {
                        if(new PremiumUser(premiumUserCount).subscribeToServer(serverABC)) {
                            premiumUserCount++;
                        }
                    } else if(choice == 2) {
                        if(new RegularUser(regularUserCount).subscribeToServer(serverABC)) {
                            regularUserCount++;
                        }
                    } else if(choice == 3) {
                        serverABC.changeServerState();
                    } else if(choice == 4) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    } else {
                        System.out.println("Enter a valid option!!");
                    }
                } catch (Exception e) {
                    System.out.println("Choice must be an integer!");
                }
            }
        }
    }
}
